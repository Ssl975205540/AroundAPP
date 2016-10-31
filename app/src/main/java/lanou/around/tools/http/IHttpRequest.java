package lanou.around.tools.http;

import java.util.Map;

/**
 * Created by dllo on 16/10/25.
 */

public interface IHttpRequest {

    /**
     * @param urlStr
     * @param tClass
     * @param listener
     * @param <T>
     */
    <T> void getRequest(String urlStr, Class<T> tClass, OnCompletedListener<T> listener);

    <T> void getRequest(String urlStr, Map<String, String> headers, Class<T> tClass, OnCompletedListener<T> listener);

    <T> void postRequest(String urlStr, Map<String, String> requestBody, Class<T> tClass, OnCompletedListener<T> listener);

    <T> void postRequest(String urlStr, Map<String, String> headers,String requestBody, Class<T> tClass, OnCompletedListener<T> listener);

    <T> void postRequest(String urlStr, Map<String, String> headers, Map<String, String> requestBody, Class<T> tClass, OnCompletedListener<T> listener);


}
