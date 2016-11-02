package lanou.around.tools.http;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dllo on 16/10/25.
 */

public class OkHttpImpl implements IHttpRequest {

    private OkHttpClient mClient;
    private Gson mGson;
    private Handler handler;

    public OkHttpImpl() {


        //规定Handler 是在主线程的
        handler = new Handler(Looper.getMainLooper());

        File file = Environment.getDownloadCacheDirectory();
        mClient = new OkHttpClient.Builder().connectTimeout(5000, TimeUnit.MILLISECONDS).cache(new Cache(file, 10 * 1024 * 1024)).build();

        mGson = new Gson();

    }

    @Override
    public <T> void getRequest(String urlStr, final Class<T> tClass, final OnCompletedListener<T> listener) {

        Request request = new Request.Builder().url(urlStr).build();

        asyncRequest(tClass, listener, request);


    }

    private <T> void postResPonse(final T result, final OnCompletedListener<T> listener) {
        handler.post(new Runnable() {
            @Override
            public void run() {

                listener.onCompleted(result);

            }
        });
    }

    private <T> void postError(final OnCompletedListener<T> listener) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onFailed();

            }
        });
    }

    @Override
    public <T> void getRequest(String urlStr, Map<String, String> headers, final Class<T> tClass, final OnCompletedListener<T> listener) {

        Request request = new Request.Builder().url(urlStr).headers(Headers.of(headers)).build();

        asyncRequest(tClass, listener, request);


    }


    @Override
    public <T> void postRequest(String urlStr, Map<String, String> requestBody, Class<T> tClass, OnCompletedListener<T> listener) {

        FormBody formBody = getFormBody(requestBody);


        Request request = new Request.Builder().url(urlStr).post(formBody).build();


        asyncRequest(tClass, listener, request);


    }

    @Override
    public <T> void postRequest(String urlStr, Map<String, String> headers, String requestBody, Class<T> tClass, OnCompletedListener<T> listener) {


        MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/x-www-form-urlencoded");
        Request request = new Request.Builder().url(urlStr).headers(Headers.of(headers)).post(RequestBody.create(MEDIA_TYPE_TEXT, requestBody)).build();


        asyncRequest(tClass, listener, request);

    }

    @NonNull
    private FormBody getFormBody(Map<String, String> requestBody) {


        FormBody.Builder builder = new FormBody.Builder();

        for (String key : requestBody.keySet()) {

            builder.add(key, requestBody.get(key));
        }


        return builder.build();
    }


    @Override
    public <T> void postRequest(String urlStr, Map<String, String> headers, Map<String, String> requestBody, Class<T> tClass, OnCompletedListener<T> listener) {

        FormBody body = getFormBody(requestBody);
        Request request = new Request.Builder().url(urlStr).post(body).headers(Headers.of(headers)).build();
        asyncRequest(tClass, listener, request);


    }


    private <T> void asyncRequest(final Class<T> tClass, final OnCompletedListener<T> listener, final Request request) {
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                postError(listener);

            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {

                    final T result = mGson.fromJson(response.body().string(), tClass);

                    postResPonse(result, listener);


                } else {

                    postError(listener);

                }


            }
        });
    }

}
