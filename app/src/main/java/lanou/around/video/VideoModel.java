package lanou.around.video;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OnCompleted;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.bean.VideoDetailsBean;
import lanou.around.tools.http.HtttpManger;
import lanou.around.tools.http.OnCompletedListener;

/**
 * Created by dllo on 16/10/26.
 */

public class VideoModel implements InterModel<VideoDetailsBean>{
    @Override
    public void StartRequest(String url, final OnFinishedListener onFinishedListener) {
        HtttpManger.getInstance().getRequest(url, VideoDetailsBean.class, new OnCompletedListener<VideoDetailsBean>() {
            @Override
            public void onCompleted(VideoDetailsBean result) {
                if (result.equals("")) {

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
    public void InsertSQ(VideoDetailsBean videoDetailsBean) {

    }

    @Override
    public <E> void QuerySQ(OnCompleted<E> onCompletedListener) {

    }
}
