package lanou.around.model;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OnCompleted;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.tools.http.HtttpManger;
import lanou.around.tools.http.OnCompletedListener;


/**
 * Created by dllo on 16/10/25.
 */

public class ClassifyTabModel implements InterModel {




    @Override
    public <T> void StartRequest(String url, Class<T> tClass, final OnFinishedListener<T> onFinishedListener) {
        HtttpManger.getInstance().getRequest(url, tClass, new OnCompletedListener<T>() {
            @Override
            public void onCompleted(T result) {
//                if (((ClassifyTabBean)result).getRespData().equals("")) {
//                    onFinishedListener.onError();
//                    return;
//                }
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
    public <E> void QuerySQ(OnCompleted<E> onCompletedListener) {

    }


}
