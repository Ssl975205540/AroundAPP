package lanou.around.model;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.tools.http.HttpManger;
import lanou.around.aroundinterface.OnCompletedListener;

/**
 * Created by dllo on 16/10/25.
 */

public class ClassifyViewModel implements InterModel {


    @Override
    public <T> void StartRequest(String url, Class<T> tClass, final OnFinishedListener<T> onFinishedListener) {


        HttpManger.getInstance().getRequest(url, tClass, new OnCompletedListener<T>() {


            @Override
            public void onCompleted(T result) {
                if (result.equals("")) {
                    onFinishedListener.onError();
                    return;
                }
                onFinishedListener.onFinished(result);
            }

            @Override
            public void onFailed() {
                onFinishedListener.onError();
            }
        });
    }

    @Override
    public <F> void InsertSQ(F t) {

    }

    @Override
    public <E> void QuerySQ(OnFinishedListener<E> onCompletedListener) {

    }

}
