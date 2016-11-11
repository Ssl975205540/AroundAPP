package lanou.around.model;

import java.util.HashMap;
import java.util.Map;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.tools.http.HttpManger;
import lanou.around.tools.http.OnCompletedListener;
import lanou.around.tools.http.URLValues;

/**
 * Created by dllo on 16/11/8.
 */

public class SeekModel implements InterModel {

    public <T> void StartRequest(String url, Class<T> tClass, String body, final OnFinishedListener<T> onFinishedListener) {
        Map<String, String> mStringMap = new HashMap<>();
        mStringMap.put(URLValues.SEARCH_HERDER_KEY, URLValues.SEARCH_HERDER_VALUE);
        HttpManger.getInstance().postRequest(url, mStringMap, body, tClass, new OnCompletedListener<T>() {
            @Override
            public void onCompleted(T result) {
                onFinishedListener.onFinished(result);
            }

            @Override
            public void onFailed() {

            }
        });
    }


    @Override
    public <T> void StartRequest(String url, Class<T> tClass, OnFinishedListener<T> onFinishedListener) {

    }

    @Override
    public <T> void InsertSQ(T t) {

    }

    @Override
    public <T> void QuerySQ(OnFinishedListener<T> onCompletedListener) {

    }
}
