package lanou.around.tools.recycle.http;

import java.util.Map;

/**
 * Created by dllo on 16/10/25.
 */
public class HtttpManger implements IHttpRequest{



    public static HtttpManger getInstance() {


        return SingletonHolder.sInstance;
    }

    private HtttpManger() {
        mRequest = new OkHttpImpl();

    }


    public static final class SingletonHolder{

        private static final HtttpManger sInstance = new HtttpManger();

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
    public <T> void postRequest(String urlStr, Map<String, String> headers, Map<String, String> requestBody, Class<T> tClass, OnCompletedListener<T> listener) {
        mRequest.postRequest(urlStr,headers,requestBody,tClass,listener);
    }
}
