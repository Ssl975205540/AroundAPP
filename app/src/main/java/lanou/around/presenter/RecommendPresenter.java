package lanou.around.presenter;

import lanou.around.aroundinterface.InterView;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.model.RecommendModel;

/**
 * Created by dllo on 16/10/31.
 */

public class RecommendPresenter {

    private InterView interView;
    private final RecommendModel homeModel;

    public RecommendPresenter(InterView interView) {

        this.interView = interView;
        homeModel = new RecommendModel();


    }


    public <T> void startRequest(String url, Class<T> tClass) {

        homeModel.StartRequest(url,tClass, new OnFinishedListener<T>() {

            @Override
            public void onFinished(T t) {

                interView.onResponse(t);

            }

            @Override
            public void onError() {


            }


        });


    }
}


