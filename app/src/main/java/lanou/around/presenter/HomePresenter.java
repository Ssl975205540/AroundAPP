package lanou.around.presenter;

import lanou.around.aroundinterface.InterView;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.bean.HomeBean;
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


    public void startRequest(String url) {

        homeModel.StartRequest(url, new OnFinishedListener<HomeBean>() {
            @Override
            public void onFinished(HomeBean homeBean) {
                interView.onResponse(homeBean);

            }

            @Override
            public void onError() {

            }
        });


    }
}
