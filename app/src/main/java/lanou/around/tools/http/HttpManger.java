package lanou.around.tools.http;

import java.util.Map;

/**
 * Created by dllo on 16/10/25.
 */
public class HttpManger implements IHttpRequest{



    public static HttpManger getInstance() {


        return SingletonHolder.sInstance;
    }

    private HttpManger() {
        mRequest = new OkHttpImpl();

    }


    public static final class SingletonHolder{

        private static final HttpManger sInstance = new HttpManger();

    }

    private IHttpRequest mRequest;



    @Override
    public <T> void getRequest(String urlStr, Class<T> tClass, OnCompletedListener<T> listener) {

        mRequest.getRequest(urlStr,tClass,listener);

    }

    @Override
    public <T> void getRequest(String urlStr, Map<String, String> headers, Class<T> tClass, OnCompletedListener<T> listener) {
        mRequest.getRequest(urlStr,headers,tClass,listener);

    }

    @Override
    public <T> void postRequest(String urlStr, Map<String, String> requestBody, Class<T> tClass, OnCompletedListener<T> listener) {

        mRequest.postRequest(urlStr,requestBody,tClass,listener);

    }

    @Override
    public <T> void postRequest(String urlStr, Map<String, String> headers, String requestBody, Class<T> tClass, OnCompletedListener<T> listener) {
        mRequest.postRequest(urlStr,headers,requestBody,tClass,listener);








    }


    @Override
    public <T> void postRequest(String urlStr, Map<String, String> headers, Map<String, String> requestBody, Class<T> tClass, OnCompletedListener<T> listener) {
        mRequest.postRequest(urlStr,headers,requestBody,tClass,listener);
    }
}
