package lanou.around.home.nearby;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.bean.HomeTabItemBean;
import lanou.around.presenter.HomeNearbyPresenter;
import lanou.around.tools.http.URLValues;
import lanou.around.tools.recycle.EncodeUtil;

/**
 * Created by dllo on 16/10/29.
 */
public class HomeTabFragment extends BaseFragment implements InterView {
    private RecyclerView recyclerView;
    private HomeTabItemAdapter adapter;
    private HomeTabItemBean bean;
    private RelativeLayout mRelative;
    private ImageView mImage;
    AnimationDrawable animationDrawable;

    @Override
    protected int setContentView() {
        return R.layout.fragment_home_tab;
    }

    @Override
    protected void initData() {
        Bundle agres = getArguments();
        String num = agres.getString("url");
        EncodeUtil encodeUtil = new EncodeUtil();
        encodeUtil.encode(num);
        String nearbyBodyURL = "pagenum=1&lon=121.544102&lat=38.883514&pagesize=20&cateId=" + num + "&";
        HomeNearbyPresenter homeNearbyPresenter = new HomeNearbyPresenter(this);
        homeNearbyPresenter.startRequest(URLValues.POST_NEARBY, nearbyBodyURL, HomeTabItemBean.class);
    }

    @Override
    protected void initViews() {
        recyclerView = findView(R.id.fragment_home_tab_recycle);
        mRelative = findView(R.id.relayout);
        mImage = findView(R.id.image_home_tab);
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
        bean = (HomeTabItemBean) t;
        adapter = new HomeTabItemAdapter(context, bean.getRespData());
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        animationDrawable = (AnimationDrawable) mImage.getBackground();
        animationDrawable.start();
        recyclerView.setAdapter(adapter);
        mRelative.setVisibility(View.GONE);
    }

    @Override
    public void onError() {

    }
}
