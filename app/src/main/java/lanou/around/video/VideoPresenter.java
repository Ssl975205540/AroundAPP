package lanou.around.video;

import lanou.around.aroundinterface.InterView;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.bean.VideoDetailsBean;

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

    public void startRequest(String url) {
        mVideoModel.StartRequest(url, new OnFinishedListener<VideoDetailsBean>() {
            @Override
            public void onFinished(VideoDetailsBean videoDetailsBean) {
                mInterView.onResponse(videoDetailsBean);
            }

            @Override
            public void onError() {

            }
        });
    }
}
