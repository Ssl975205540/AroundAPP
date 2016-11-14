package lanou.around.model;

import java.util.HashMap;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.tools.http.HttpManger;
import lanou.around.tools.http.OnCompletedListener;


/**
 * Created by dllo on 16/11/3.
 */

public class HomeNearbyModel implements InterModel{
    @Override
    public <T> void StartRequest(String url, Class<T> tClass, final OnFinishedListener<T> onFinishedListener) {
        HashMap<String , String> hashMap = new HashMap<>();
        hashMap.put("Cookie" , "t=15;tk=ED7454894632EB3106DE5F63CA35708A;v=2.5.0;channelid=market_908;lat=38.883514;lon=121.544102;");
        HttpManger.getInstance().postRequest(url, hashMap, "pagenum=1&lon=121.544102&lat=38.883514&pagesize=20&", tClass, new OnCompletedListener<T>() {
            @Override
            public void onCompleted(T result) {
                onFinishedListener.onFinished(result);
            }

            @Override
            public void onFailed() {

            }
        });
    }



    public <T> void StartRequest(String url, Class<T> tClass,String body, final OnFinishedListener<T> onFinishedListener) {
        HashMap<String , String> hashMap = new HashMap<>();
        hashMap.put("Cookie" , "t=15;tk=ED7454894632EB3106DE5F63CA35708A;v=2.5.0;channelid=market_908;lat=38.883514;lon=121.544102;");
        HttpManger.getInstance().postRequest(url, hashMap, body, tClass, new OnCompletedListener<T>() {
            @Override
            public void onCompleted(T result) {
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
    public <T> void QuerySQ(OnFinishedListener<T> onCompletedListener) {

    }
}
