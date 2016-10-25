package lanou.around.aroundinterface;

/**
 * Created by dllo on 16/10/25.
 */

public interface InterModel {

    void StartRequest(String url,OnFinishedListener onFinishedListener);

    <T> void InsertSQ(T t);

    void QuerySQ();

}
