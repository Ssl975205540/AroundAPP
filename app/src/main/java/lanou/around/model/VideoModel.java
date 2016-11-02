package lanou.around.model;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OnCompleted;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.bean.VideoDetailsBean;
import lanou.around.tools.http.HttpManger;
import lanou.around.tools.http.OnCompletedListener;


/**
 * Created by dllo on 16/10/26.
 */

public class VideoModel implements InterModel{
   


    @Override
    public <T> void StartRequest(String url, Class<T> tClass, final OnFinishedListener<T> onFinishedListener) {

        HttpManger.getInstance().getRequest(url, tClass, new OnCompletedListener<T>() {

            @Override
            public void onCompleted(T result) {
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


        HttpManger.getInstance().postRequest(url, null, VideoDetailsBean.class, new OnCompletedListener<VideoDetailsBean>() {
            @Override
            public void onCompleted(VideoDetailsBean result) {

            }

            @Override
            public void onFailed() {

            }
        });


    }

    @Override
    public <F> void InsertSQ(F t) {

    }

    @Override
    public <E> void QuerySQ(OnCompleted<E> onCompletedListener) {

    }
}
