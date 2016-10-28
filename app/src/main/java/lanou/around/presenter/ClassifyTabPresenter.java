package lanou.around.presenter;

import lanou.around.aroundinterface.InterView;
import lanou.around.aroundinterface.OnFinishedListener;
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

    public <T> void startRequest(String url, Class<T> tClass) {
        mClassifyTabModel.StartRequest(url, tClass, new OnFinishedListener<T>() {


            @Override
            public void onFinished(T t) {
                mInterView.onResponse(t);
            }

            @Override
            public void onError() {
                mInterView.onError();
            }
        });
    }
}
