package lanou.around.home.recommend;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.bean.RecommendBean;
import lanou.around.widget.MyRecyclerView;

/**
 * Created by dllo on 16/10/22.
 */

public class RecommendFragment extends BaseFragment implements InterView {

    private MyRecyclerView recyclerview;

    @Override
    protected void initData() {

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

    }

    @Override
    public void startAnimation() {

    }

    @Override
    public void stopAnimation() {

    }

    @Override
    public <T> void onResponse(T t) {


        RecommendBean bean = (RecommendBean) t;
        RecommendAdapter recommendAdapter = new RecommendAdapter(context, bean.getRespData());
    }

    @Override
    public void onError() {

    }
}
