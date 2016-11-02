package lanou.around.home.recommend;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.bean.RecommendBean;
import lanou.around.presenter.RecommendPresenter;

/**
 * Created by dllo on 16/10/22.
 */

public class RecommendFragment extends BaseFragment implements InterView {

    private RecyclerView recyclerview;
    private int newState;
    private RecommendAdapter recommendAdapter;

    @Override
    protected void initData() {

        RecommendPresenter recommendPresenter = new RecommendPresenter(this);

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



        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {


            public boolean dasd = true;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visible  = linearLayoutManager.getChildCount();
                int total = linearLayoutManager.getItemCount();
                int past=linearLayoutManager.findLastVisibleItemPosition();

                if (total-1 ==past){

                    if(dasd){
                        Log.d("sahbi","hhh");
                        dasd =false;
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
        recyclerview.setAdapter(recommendAdapter);
    }

    @Override
    public void onError() {


    }
}
