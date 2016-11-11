package lanou.around.home.nearby;

import lanou.around.aroundinterface.InterView;
import lanou.around.aroundinterface.OnFinishedListener;

/**
 * Created by dllo on 16/11/9.
 */
public class HomeItemPresenter {
    private InterView interView;
    private HomeItemModel mItemModel;

    public HomeItemPresenter(InterView interView) {
        this.interView = interView;
        mItemModel = new HomeItemModel();
    }
    public <T> void startRequest(String url, Class<T> tClass) {
        mItemModel.StartRequest(url,tClass, new OnFinishedListener<T>() {
            @Override
            public void onFinished(T t) {
                interView.onResponse(t);
            }

            @Override
            public void onError() {

            }
        });
    }
}
