package lanou.around.presenter;

import lanou.around.aroundinterface.InterView;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.model.VideoModel;

/**
 * Created by dllo on 16/10/26.
 */

public class VideoPresenter {
    private InterView mInterView;
    private final VideoModel mVideoModel;

    public VideoPresenter(InterView mInterView) {
        this.mInterView = mInterView;
        mVideoModel = new VideoModel();
    }

    public <T> void startRequest(String url,Class<T> tClass) {
        mVideoModel.StartRequest(url, tClass ,new OnFinishedListener<T>() {


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
