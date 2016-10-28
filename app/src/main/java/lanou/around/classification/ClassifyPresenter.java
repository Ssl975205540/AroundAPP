package lanou.around.classification;

import lanou.around.aroundinterface.InterClassifyView;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.bean.ClassifyBean;

/**
 * Created by dllo on 16/10/26.
 */

public class ClassifyPresenter {
    private InterClassifyView mInterClassifyView;
    private ClassifyModel mClassifyModel;

    public ClassifyPresenter(InterClassifyView interClassifyView) {
        mInterClassifyView = interClassifyView;
        mClassifyModel = new ClassifyModel();
    }

    public <T> void startRequest(String url,Class<T> tClass) {
        mClassifyModel.StartRequest(url,tClass, new OnFinishedListener<T>() {


            @Override
            public void onFinished(T t) {
                mInterClassifyView.onClassifyResponse(t);

            }

            @Override
            public void onError() {
                mInterClassifyView.onError();
            }
        });
    }
}
