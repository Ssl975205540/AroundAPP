package lanou.around.model;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.bean.HomeBean;
import lanou.around.tools.recycle.http.HtttpManger;
import lanou.around.tools.recycle.http.OnCompletedListener;

/**
 * Created by dllo on 16/10/25.
 */

public class HomeModel implements InterModel{


    @Override
    public void StartRequest(String url , final OnFinishedListener onFinishedListener) {

        HtttpManger.getInstance().getRequest(url, HomeBean.class, new OnCompletedListener<HomeBean>() {
            @Override
            public void onCompleted(HomeBean result) {

                onFinishedListener.onFinished(result);

            }

            @Override
            public void onFailed() {



            }
        });

    }

    @Override
    public <T> void InsertSQ(T t) {

    }

    @Override
    public void QuerySQ() {

    }
}
