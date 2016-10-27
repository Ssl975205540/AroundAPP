package lanou.around.classification.classifiview;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OnCompleted;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.bean.ClassifyViewBean;
import lanou.around.tools.http.HtttpManger;
import lanou.around.tools.http.OnCompletedListener;

/**
 * Created by dllo on 16/10/25.
 */

public class ClassifyViewModel implements InterModel<ClassifyViewBean> {
    @Override
    public void StartRequest(String url, final OnFinishedListener onFinishedListener) {
        HtttpManger.getInstance().getRequest(url, ClassifyViewBean.class, new OnCompletedListener<ClassifyViewBean>() {
            @Override
            public void onCompleted(ClassifyViewBean result) {
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
    public void InsertSQ(ClassifyViewBean classifyViewBean) {

    }

    @Override
    public <E> void QuerySQ(OnCompleted<E> onCompletedListener) {

    }

}
