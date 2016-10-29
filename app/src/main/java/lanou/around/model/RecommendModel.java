package lanou.around.model;

import java.util.HashMap;

import lanou.around.aroundinterface.InterModel;
import lanou.around.aroundinterface.OnCompleted;
import lanou.around.aroundinterface.OnFinishedListener;

/**
 * Created by dllo on 16/10/28.
 */

public class RecommendModel implements InterModel {
    @Override
    public <T> void StartRequest(String url, Class<T> tClass, OnFinishedListener<T> onFinishedListener) {
       HashMap<String,String> hashMap = new HashMap<>();

//       URLValues hashMap.put("")
//        HtttpManger.getInstance().postRequest(url,tClass,);

    }

    @Override
    public <F> void InsertSQ(F t) {

    }

    @Override
    public <E> void QuerySQ(OnCompleted<E> onCompletedListener) {

    }
}
