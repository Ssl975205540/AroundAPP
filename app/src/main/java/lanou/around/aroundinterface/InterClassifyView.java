package lanou.around.aroundinterface;

/**
 * Created by dllo on 16/10/26.
 */

public interface InterClassifyView<E> {

    void onClassifyResponse(E e);

    void onError();
}
