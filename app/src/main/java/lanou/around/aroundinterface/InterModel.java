package lanou.around.aroundinterface;

/**
 * Created by dllo on 16/10/25.
 */

public interface InterModel {

    <T>void StartRequest(String url,Class<T> tClass,OnFinishedListener<T> onFinishedListener);

    <T>void InsertSQ(T t);

   <T>void QuerySQ(OnCompleted<T> onCompletedListener);

}
