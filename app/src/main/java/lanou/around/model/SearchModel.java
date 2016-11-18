package lanou.around.model;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.tools.http.HttpManger;
import lanou.around.aroundinterface.OnCompletedListener;

/**
 * Created by dllo on 16/11/2.
 */

public class SearchModel implements InterModel {
    private String body;

    @Override
    public <T> void StartRequest(String url, Class<T> tClass, final OnFinishedListener<T> onFinishedListener) {
        HttpManger.getInstance().postRequest(url, null, body, tClass, new OnCompletedListener<T>() {
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
    public <T> void InsertSQ(T t) {

    }

    @Override
    public <T> void QuerySQ(OnFinishedListener<T> onCompletedListener) {

    }


    public void setBody(String body) {
        this.body = body;
    }
}
