package lanou.around.presenter;

import lanou.around.aroundinterface.InterView;
import lanou.around.aroundinterface.OnFinishedListener;
import lanou.around.model.SearchModel;

/**
 * Created by dllo on 16/11/2.
 */

public class SearchPresenter {

    private InterView mInterView;
    private final SearchModel mSearchModel;

    public SearchPresenter(InterView interView,String body) {
        mInterView = interView;
        mSearchModel = new SearchModel();

        mSearchModel.setBody(body);
    }

    public <T> void startRequest(String url, Class<T> tClass) {
        mSearchModel.StartRequest(url, tClass, new OnFinishedListener<T>() {
            @Override
            public void onFinished(T t) {
                mInterView.onResponse(t);
            }

            @Override
            public void onError() {

            }
        });
    }

}
