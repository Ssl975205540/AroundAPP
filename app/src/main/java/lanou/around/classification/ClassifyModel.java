package lanou.around.classification;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OnCompleted;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.bean.ClassifyBean;
import lanou.around.tools.http.HtttpManger;
import lanou.around.tools.http.OnCompletedListener;

/**
 * Created by dllo on 16/10/26.
 */
public class ClassifyModel implements InterModel<ClassifyBean> {
    @Override
    public void StartRequest(String url, final OnFinishedListener onFinishedListener) {
        HtttpManger.getInstance().getRequest(url, ClassifyBean.class, new OnCompletedListener<ClassifyBean>() {
            @Override
            public void onCompleted(ClassifyBean result) {
                if (result.getRespData().equals("")) {
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
    public void InsertSQ(ClassifyBean classifyBean) {

    }

    @Override
    public <E> void QuerySQ(OnCompleted<E> onCompletedListener) {

    }
}
