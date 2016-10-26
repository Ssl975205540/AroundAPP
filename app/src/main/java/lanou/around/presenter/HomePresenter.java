package lanou.around.presenter;

import android.widget.Toast;

import java.util.ArrayList;

import lanou.around.app.AroundAPP;
import lanou.around.aroundinterface.InterView;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.bean.HomeBean;
import lanou.around.bean.HomeBeanHot;
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

                homeModel.InsertSQ(homeBean);

            }

            @Override
            public void onError() {

                ArrayList<HomeBeanHot> arrayList = new ArrayList<HomeBeanHot>();
                if(arrayList.size() != 0){

                    interView.onError();

                }else {

                    Toast.makeText(AroundAPP.getContext(), "网络不可用", Toast.LENGTH_SHORT).show();
                }


            }


        });


    }
}
