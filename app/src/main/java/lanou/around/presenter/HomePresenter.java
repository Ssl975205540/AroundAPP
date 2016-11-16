package lanou.around.presenter;

import lanou.around.app.AroundAPP;
import lanou.around.aroundinterface.InterView;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.model.HomeModel;

/**
 * Created by dllo on 16/10/25.
 */

public class HomePresenter {

    private InterView interView;
    private final HomeModel homeModel;

    public HomePresenter(InterView interView) {

        this.interView = interView;
        homeModel = new HomeModel();


    }


    public <T> void startRequest(String url, Class<T> tClass) {

        if(AroundAPP.isNetworkAvailable() == false){
            interView.onError();
        }

        homeModel.StartRequest(url,tClass, new OnFinishedListener<T>() {

            @Override
            public void onFinished(T t) {


                interView.onResponse(t);
                if(AroundAPP.isNetworkAvailable()){
                    homeModel.InsertSQ(t);
                }


            }

            @Override
            public void onError() {


            }


        });


    }
}
