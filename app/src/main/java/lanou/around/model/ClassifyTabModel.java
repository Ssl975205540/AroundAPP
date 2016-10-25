package lanou.around.model;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.bean.ClassifyTabBean;
import lanou.around.tools.recycle.http.HtttpManger;
import lanou.around.tools.recycle.http.OnCompletedListener;

/**
 * Created by dllo on 16/10/25.
 */

public class ClassifyTabModel implements InterModel {
    @Override
    public void StartRequest(String url, final OnFinishedListener onFinishedListener) {
        HtttpManger.getInstance().getRequest(url, ClassifyTabBean.class, new OnCompletedListener<ClassifyTabBean>() {
            @Override
            public void onCompleted(ClassifyTabBean result) {
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
    public <T> void InsertSQ(T t) {

    }

    @Override
    public void QuerySQ() {

    }
}
