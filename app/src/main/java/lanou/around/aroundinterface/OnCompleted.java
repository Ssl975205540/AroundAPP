package lanou.around.aroundinterface;

/**
 * Created by dllo on 16/10/25.
 */

public interface OnCompleted<T> {

    void onCompleted(T result);

    void onFailed();


}
