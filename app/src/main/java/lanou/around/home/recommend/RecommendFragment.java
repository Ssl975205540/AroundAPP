package lanou.around.home.recommend;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
    private int newState;

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
    public void onResponse(Object t) {

        RecommendBean bean = (RecommendBean) t;

        final RecommendAdapter recommendAdapter = new RecommendAdapter(context);

        recommendAdapter.setData(bean.getRespData());
        recyclerview.setAdapter(recommendAdapter);

        recyclerview.setLayoutManager(new LinearLayoutManager(context));

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                RecyclerView.LayoutManager layoutManager = recyclerview.getLayoutManager();
//判断是当前layoutManager是否为LinearLayoutManager
//只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    int lastItemPosition = linearManager.findLastVisibleItemPosition();
                    //获取第一个可见view的位置
                    int firstItemPosition = linearManager.findFirstVisibleItemPosition();

                    Log.d("111", String.valueOf(firstItemPosition));



                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastItemPosition + 1 == recommendAdapter.getItemCount()) {
                        //最后一个itemView的position为adapter中最后一个数据时,说明该itemView就是底部的view了
                        //需要注意position从0开始索引,adapter.getItemCount()是数据量总数
                    }
                    //同理检测是否为顶部itemView时,只需要判断其位置是否为0即可
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && firstItemPosition == 0) {
                    }

                }
            }
        });
    }

    @Override
    public void onError() {


    }
}
