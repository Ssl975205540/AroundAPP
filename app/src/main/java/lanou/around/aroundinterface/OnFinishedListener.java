package lanou.around.aroundinterface;


/**
 * Created by dllo on 16/10/24.
 */

public interface OnFinishedListener<T> {

    void onFinished(T t);

    <E>void onError(E e);



}
