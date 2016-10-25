package lanou.around.presenter;

import lanou.around.aroundinterface.InterView;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.bean.ClassifyTabBean;
import lanou.around.model.ClassifyTabModel;

/**
 * Created by dllo on 16/10/25.
 */

public class ClassifyTabPresenter {

    private InterView mInterView;
    private final ClassifyTabModel mClassifyTabModel;

    public ClassifyTabPresenter(InterView interView) {
        this.mInterView = interView;
        mClassifyTabModel = new ClassifyTabModel();
    }

    public void startRequest(String url) {
        mClassifyTabModel.StartRequest(url, new OnFinishedListener<ClassifyTabBean>() {
            @Override
            public void onFinished(ClassifyTabBean tabBean) {
                mInterView.onResponse(tabBean);
            }

            @Override
            public void onError() {
                mInterView.onError();
            }
        });
    }
}
