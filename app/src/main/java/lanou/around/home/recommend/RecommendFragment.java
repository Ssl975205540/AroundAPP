package lanou.around.home.recommend;

import android.support.v7.widget.LinearLayoutManager;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.bean.RecommendBean;
import lanou.around.presenter.RecommendPresenter;
import lanou.around.widget.MyRecyclerView;

/**
 * Created by dllo on 16/10/22.
 */

public class RecommendFragment extends BaseFragment implements InterView {

    private MyRecyclerView recyclerview;

    @Override
    protected void initData() {

        RecommendPresenter recommendPresenter = new RecommendPresenter(this);

        recommendPresenter.startRequest("http://zhuanzhuan.58.com/zz/transfer/getRecommendInfoForIndex",RecommendBean.class);
    }

    @Override
    protected int setContentView() {
        return R.layout.recommend_fragment;
    }

    @Override
    protected void initViews() {

        recyclerview = findView(R.id.recyclerview_recommend);

    }

    @Override
    protected void initListeners() {


        recyclerview.setLoadingListener(new MyRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void setdisplay(int i) {

            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    @Override
    public void startAnimation() {

    }

    @Override
    public void stopAnimation() {

    }

    @Override
    public  void onResponse(Object t) {

        RecommendBean bean = (RecommendBean) t;

        RecommendAdapter recommendAdapter = new RecommendAdapter(context, bean.getRespData());

        recyclerview.setAdapter(recommendAdapter);

        recyclerview.setLayoutManager(new LinearLayoutManager(context));





    }

    @Override
    public void onError() {




    }
}
