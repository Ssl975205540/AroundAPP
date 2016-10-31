package lanou.around.presenter;

import lanou.around.aroundinterface.InterView;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.model.ClassifyViewModel;


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

    public <T> void startRequest(String url,Class<T> tClass) {
        mClassifyViewModel.StartRequest(url, tClass ,new OnFinishedListener<T>() {


            @Override
            public void onFinished(T t) {
                mInterView.onResponse(t);
            }

            @Override
            public void onError() {

            }
        });
    }


}
