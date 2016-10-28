package lanou.around.aroundinterface;

/**
 * Created by dllo on 16/10/25.
 */

public interface InterModel{

    <T>void StartRequest(String url,Class<T> tClass,OnFinishedListener<T> onFinishedListener);

    <F>void InsertSQ(F t);

   <E>void QuerySQ(OnCompleted<E> onCompletedListener);

}
