package lanou.around.classification;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OCompleted;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.tools.recycle.http.HtttpManger;
import lanou.around.tools.recycle.http.OnCompletedListener;

/**
 * Created by dllo on 16/10/26.
 */
public class ClassifyModel implements InterModel {




    @Override
    public <T> void StartRequest(String url, Class<T> tClass, final OnFinishedListener<T> onFinishedListener) {

        HtttpManger.getInstance().getRequest(url, tClass, new OnCompletedListener<T>() {


            @Override
            public void onCompleted(T result) {
                if (((ClassifyBean)result).getRespData().equals("")) {
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
    public <E> void QuerySQ(OCompleted<E> onCompletedListener) {

    }
}
