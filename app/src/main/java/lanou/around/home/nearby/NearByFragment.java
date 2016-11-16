package lanou.around.home.nearby;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.bean.HomeTabBean;
import lanou.around.presenter.HomePresenter;
import lanou.around.tools.http.URLValues;

/**
 * Created by dllo on 16/10/22.
 */

public class NearByFragment extends BaseFragment
        implements View.OnClickListener, InterView {

    private TabLayout mTitleTab;
    private ViewPager mViewPager;
    private ImageView mDropDownButton;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> strings = new ArrayList<>();
    private List<String> mStringList = new ArrayList<>();
    private HomeTabFragment mFragment;
    private Bundle args;


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
    public void onResponse(Object bean) {
        if (bean instanceof HomeTabBean) {
            HomeTabBean homeTabBean = (HomeTabBean) bean;
            for (int i = 0; i < homeTabBean.getRespData().size(); i++) {
                strings.add(homeTabBean.getRespData().get(i).getCateName());
                mStringList.add(homeTabBean.getRespData().get(i).getSubName());
            }
            strings.add(0, "全部");
            mStringList.add(0, "都在这里");

            for (int i = 0; i < strings.size(); i++) {

                if (i == 0) {
                    args = new Bundle();
                    args.putString("url", "");
                } else {
                    args = new Bundle();
                    args.putString("url", homeTabBean.getRespData().get(i - 1).getCateId());
                }

                mFragment = new HomeTabFragment();
                mFragment.setArguments(args);
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
        switch (view.getId()) {
            case R.id.iv_dropDownButton:
                //自定义布局,显示内容

                View view1 = LayoutInflater.from(context).inflate(R.layout.popup_window_top, null);
                RecyclerView rv_popupWindow = (RecyclerView) view1.findViewById(R.id.rv_popup_window);
                ImageView dropUpButton = (ImageView) view1.findViewById(R.id.iv_dropUpButton);
                PopupWindowAdapter adapter = new PopupWindowAdapter(context);
                adapter.setArrayList(strings, mStringList);
                GridLayoutManager manager = new GridLayoutManager(context, 3);
                rv_popupWindow.setLayoutManager(manager);
                rv_popupWindow.setAdapter(adapter);

                final PopupWindow popupWindow = new PopupWindow(view1, ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT, true);
                popupWindow.setTouchable(true);
                dropUpButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (popupWindow.isShowing()) {
                            popupWindow.dismiss();
                        }
                    }
                });

                adapter.setPopupOnItemClickListener(new PopupWindowAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClickListener(View view, int position) {
                        mViewPager.setCurrentItem(position);
                        popupWindow.dismiss();
                    }
                });

                popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.item_popup));
//                // 设置好参数之后再show
                popupWindow.showAsDropDown(view, 0, -98);


                break;
        }
    }
}
