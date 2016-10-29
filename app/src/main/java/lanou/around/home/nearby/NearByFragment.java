package lanou.around.home.nearby;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.bean.ClassifyTabBean;
import lanou.around.home.HomeTabBean;
import lanou.around.presenter.HomePresenter;
import lanou.around.tools.http.URLValues;

/**
 * Created by dllo on 16/10/22.
 */

public class NearByFragment extends BaseFragment implements View.OnClickListener, InterView {


    private TabLayout mTitleTab;
    private ViewPager mViewPager;
    private ImageView mDropDownButton;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> strings = new ArrayList<>();
    private HomeTabFragment mFragment;

    @Override
    protected int setContentView() {
        return R.layout.nearby_fragment;
    }

    @Override
    protected void initViews() {
        mTitleTab = findView(R.id.tb_home_top);
        mViewPager = findView(R.id.vp_home_top);
        mDropDownButton = findView(R.id.iv_dropDownButton);
        mDropDownButton.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        HomePresenter presenter = new HomePresenter(this);
        presenter.startRequest(URLValues.HOME_NEAR_TAB_MARKET, HomeTabBean.class);
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
    public void onResponse(Object bean) {
        if (bean instanceof HomeTabBean) {
            HomeTabBean homeTabBean = (HomeTabBean) bean;
            for (int i = 0; i < homeTabBean.getRespData().size(); i++) {
                strings.add(homeTabBean.getRespData().get(i).getCateName());
                Log.d("NearByFragment", homeTabBean.getRespData().get(i).getCateName());
            }
            strings.add(0, "全部");
            for (int i = 0; i < strings.size(); i++) {
                mFragment = new HomeTabFragment();
                fragments.add(mFragment);
            }
        }
        HomeTabAdapter adapter = new HomeTabAdapter(getChildFragmentManager());
        adapter.setFragments(fragments);
        adapter.setStrings(strings);
        mViewPager.setAdapter(adapter);
        mTitleTab.setupWithViewPager(mViewPager);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onClick(View view) {

    }
}
