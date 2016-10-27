package lanou.around.classification;

import lanou.around.aroundinterface.InterClassifyView;
import lanou.around.aroundinterface.OnFinishedListener;

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

    public void startRequest(String url) {
        mClassifyModel.StartRequest(url, new OnFinishedListener<ClassifyBean>() {
            @Override
            public void onFinished(ClassifyBean bean) {
                mInterClassifyView.onClassifyResponse(bean);
            }

            @Override
            public void onError() {
                mInterClassifyView.onError();
            }
        });
    }
}
