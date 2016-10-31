package lanou.around.model;

import java.util.HashMap;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OnCompleted;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.tools.http.HtttpManger;
import lanou.around.tools.http.OnCompletedListener;

/**
 * Created by dllo on 16/10/28.
 */

public class RecommendModel implements InterModel {


    @Override
    public <T> void StartRequest(String url, Class<T> tClass, final OnFinishedListener<T> onFinishedListener) {


        HashMap<String,String> hashMap = new HashMap<>();


        hashMap.put("Cookie","t=15;tk=2A696298079D4818C2AA33056223640E;v=2.4.4;channelid=market_913;lat=38.883297;lon=121.544435;model=vivo X6D;uid=42878881258263;PPU=\"UID=42878881258263&PPK=81f65a86&PPT=93c14d7b&SK=6132DEA395E09C43AC9DC477B5CB14E52E7EF807C52AECEFB&LT=1477890354315&UN=xtgj331&LV=e5d60885&PBODY=VcCVMYwjGwTFRA7pM8iplClptAw6kGPWMhVe8O391sCrDxUYB3fXp2cci_3rQ7yYiei1XG3SLLitSV5jkYi0RczB1JWshPqGNauu6BbI_efvtE7ZMJUqixW2h7FKor3KfXkPBhcoK3AbTvXOyVnrJJaAUSHsfVJXBiFkgUqp1Tk&VER=1\"; Version=1; Domain=58.com; Path=/;");

        HtttpManger.getInstance().postRequest(url, hashMap, "pageNum=1&lat=38.883297&lng=121.544435&pageSize=20&", tClass, new OnCompletedListener<T>() {
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
    public <T> void QuerySQ(OnCompleted<T> onCompletedListener) {

    }
}
