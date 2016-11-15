package lanou.around.model;

import java.util.HashMap;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.tools.http.HttpManger;
import lanou.around.aroundinterface.OnCompletedListener;
import lanou.around.tools.http.URLValues;

/**
 * Created by dllo on 16/11/9.
 */
public class HomeItemModel implements InterModel {
    @Override
    public <T> void StartRequest(String url, Class<T> tClass, final OnFinishedListener<T> onFinishedListener) {
        HashMap<String , String> hashMap = new HashMap<>();
        hashMap.put("Cookie" , URLValues.PIN_RECOMMEND_JUMP_HEADER);
        HttpManger.getInstance().postRequest(url, hashMap, URLValues.PIN_RECOMMEND_JUMP_BODY, tClass, new OnCompletedListener<T>() {
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
