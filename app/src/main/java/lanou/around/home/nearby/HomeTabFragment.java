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
import lanou.around.bean.HomeItemBean;
import lanou.around.bean.HomeTabItemBean;
import lanou.around.home.recommend.RecommendWebView;
import lanou.around.presenter.HomeNearbyPresenter;
import lanou.around.tools.http.URLValues;
import lanou.around.tools.util.EncodeUtil;
import lanou.around.tools.util.IntentUtils;

import static lanou.around.home.recommend.RecommendWebView.INFO_URL_JUMP;

/**
 * Created by dllo on 16/10/29.
 */
public class HomeTabFragment extends BaseFragment implements InterView {
    private RecyclerView recyclerView;
    private HomeTabItemAdapter adapter;
    private HomeTabItemBean bean;
    private RelativeLayout mRelative;
    private ImageView mImage;
    private AnimationDrawable animationDrawable;
    private HomeItemBean mItemBean;
    protected boolean isVisible;

    @Override
    protected int setContentView() {
        return R.layout.fragment_home_tab;
    }

    @Override
    protected void initData() {



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




        if (t instanceof HomeTabItemBean) {
            bean = (HomeTabItemBean) t;

            adapter = new HomeTabItemAdapter(context, bean.getRespData());
            recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            recyclerView.setAdapter(adapter);
            adapter.setClickListener(new HomeTabItemAdapter.OnRcvItemClickListener() {
                @Override
                public void onRcvClickListener(View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString(INFO_URL_JUMP, mItemBean.getRespData().getInfoUrl());
                    IntentUtils.getIntents().Intent(context, RecommendWebView.class, bundle);
                }
            });
        }

        if (t instanceof HomeItemBean) {
            mItemBean = (HomeItemBean) t;
        }

        mRelative.setVisibility(View.GONE);
    }

    @Override
    public void onError() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    private void onInvisible() {


    }


    private void onVisible() {

        if(animationDrawable != null){
            animationDrawable = (AnimationDrawable) mImage.getBackground();
            animationDrawable.start();
        }

        Bundle agres = getArguments();
        String num = agres.getString("url");
        EncodeUtil encodeUtil = new EncodeUtil();
        encodeUtil.encode(num);
        String nearbyBodyURL = "pagenum=1&lon=121.544102&lat=38.883514&pagesize=20&cateId=" + num + "&";
        HomeNearbyPresenter homeNearbyPresenter = new HomeNearbyPresenter(this);
        homeNearbyPresenter.startRequest(URLValues.POST_NEARBY, nearbyBodyURL, HomeTabItemBean.class);

        HomeItemPresenter itemPresenter = new HomeItemPresenter(this);
        itemPresenter.startRequest(URLValues.PIN_RECOMMEND_JUMP, HomeItemBean.class);

    }

}
