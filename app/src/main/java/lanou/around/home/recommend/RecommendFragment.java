package lanou.around.home.recommend;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.bean.RecommendBean;
import lanou.around.presenter.RecommendPresenter;

/**
 * Created by dllo on 16/10/22.
 */

public class RecommendFragment extends BaseFragment implements InterView {
    public boolean loading = true;
    private RecyclerView recyclerview;
    private RecommendAdapter recommendAdapter;
    private RecommendPresenter recommendPresenter;

    @Override
    protected void initData() {

        recommendPresenter = new RecommendPresenter(this);

        recommendPresenter.startRequest("http://zhuanzhuan.58.com/zz/transfer/getRecommendInfoForIndex", RecommendBean.class);
    }

    @Override
    protected int setContentView() {
        return R.layout.recommend_fragment;
    }

    @Override
    protected void initViews() {

        recyclerview = findView(R.id.recyclerview_recommend);

        recommendAdapter = new RecommendAdapter(context);

        recyclerview.setAdapter(recommendAdapter);


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {



            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visible  = linearLayoutManager.getChildCount();
                int total = linearLayoutManager.getItemCount();
                int past=linearLayoutManager.findLastVisibleItemPosition();

                if (total-1 ==past){

                    if(loading){

                        recommendPresenter.startRequest("http://zhuanzhuan.58.com/zz/transfer/getRecommendInfoForIndex", RecommendBean.class);

                        loading =false;
                    }

                }
            }
        });

    }

    @Override
    protected void initListeners() {


    }

    @Override
    public void startAnimation() {

    }

    @Override
    public void stopAnimation() {

    }

    @Override
    public void onResponse(Object t) {

        RecommendBean bean = (RecommendBean) t;

//        Listv listv = new Listv(context,bean.getRespData());
//        recyclerview.setAdapter(listv);


        recommendAdapter.setData(bean.getRespData());

        loading =true;
    }

    @Override
    public void onError() {


    }
}
