package lanou.around.presenter;

import lanou.around.aroundinterface.InterView;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.model.HomeNearbyModel;

/**
 * Created by dllo on 16/11/3.
 */

public class HomeNearbyPresenter {
    private InterView interView;
    private HomeNearbyModel nearbyModel;

    public HomeNearbyPresenter(InterView interView) {
        this.interView = interView;
        nearbyModel = new HomeNearbyModel();
    }
    public <T> void startRequest(String url, Class<T> tClass) {
        nearbyModel.StartRequest(url,tClass, new OnFinishedListener<T>() {
            @Override
            public void onFinished(T t) {
                interView.onResponse(t);
            }

            @Override
            public void onError() {

            }
        });
    }
    public <T> void startRequest(String url,String body, Class<T> tClass) {
        nearbyModel.StartRequest(url,tClass,body, new OnFinishedListener<T>() {
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
