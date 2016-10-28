package lanou.around.aroundinterface;

/**
 * Created by dllo on 16/10/25.
 */

public interface InterView  {

    void startAnimation();

    void stopAnimation();

    <T>void onResponse(T t);

    void onError();



}
