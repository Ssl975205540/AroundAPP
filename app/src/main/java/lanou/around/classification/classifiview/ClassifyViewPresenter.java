package lanou.around.classification.classifiview;

import lanou.around.aroundinterface.InterView;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.bean.ClassifyViewBean;

/**
 * Created by dllo on 16/10/25.
 */
public class ClassifyViewPresenter {
    private InterView mInterView;
    private ClassifyViewModel mClassifyViewModel;

    public ClassifyViewPresenter(InterView interView) {
        mInterView = interView;
        mClassifyViewModel = new ClassifyViewModel();
    }

    public void startRequest(String url) {
        mClassifyViewModel.StartRequest(url, new OnFinishedListener<ClassifyViewBean>() {
            @Override
            public void onFinished(ClassifyViewBean viewBean) {
                mInterView.onResponse(viewBean);
            }

            @Override
            public void onError() {

            }
        });
    }


}
