package lanou.around.aroundinterface;

/**
 * Created by dllo on 16/10/25.
 */

public interface InterModel<T>{

    void StartRequest(String url,OnFinishedListener onFinishedListener);

    void InsertSQ(T t);

   <E>void QuerySQ(OCompleted<E> onCompletedListener);

}
