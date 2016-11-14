package lanou.around.presenter;

import lanou.around.aroundinterface.InterView;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.model.SeekModel;

/**
 * Created by dllo on 16/11/8.
 */

public class SeekPresenter {
    private InterView mInterView;
    private SeekModel mSeekModel;

    public SeekPresenter(InterView interView) {
        this.mInterView = interView;
        mSeekModel = new SeekModel();
    }

    public <T> void startRequest(String url, Class<T> tClass) {
        mSeekModel.StartRequest(url,tClass, new OnFinishedListener<T>() {
            @Override
            public void onFinished(T t) {
                mInterView.onResponse(t);
            }

            @Override
            public void onError() {

            }
        });
    }

    public <T> void startRequest(String url, String body, Class<T> tClass) {
        mSeekModel.StartRequest(url, tClass, body, new OnFinishedListener<T>() {
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
