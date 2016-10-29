package lanou.around.home;

import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.app.AroundAPP;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.bean.HomeBean;
import lanou.around.bean.HomeBeanHot;
import lanou.around.home.nearby.NearByFragment;
import lanou.around.home.recommend.RecommendFragment;
import lanou.around.presenter.HomePresenter;
import lanou.around.tools.http.URLValues;
import lanou.around.widget.MyRecyclerView;
import lanou.around.widget.StretchAnimation;
import lanou.around.widget.TransparentToolBar;
import lanou.around.widget.WrapContentHeightViewPager;

/**
 * Created by dllo on 16/10/22.
 */

public class HomeFragment extends BaseFragment implements InterView, TransparentToolBar.OnScrollStateListener, StretchAnimation.AnimationListener, View.OnClickListener {

    private MyRecyclerView recyviewHome;
    private WrapContentHeightViewPager viewPagerHome;
    private TabLayout tabHome;
    private HomeAdapter homeAdapter;
    private int d;
    private HomePresenter homePresenter;
    private AppBarLayout homeAppbar;
    private TransparentToolBar toolbarHome;
    private NestedScrollView nestscrollHome;
    private int statusBarHeight;
    private RelativeLayout rl;
    private RelativeLayout rl1, rl0;
    private TextView tvSpending, tvSpent;
    private ImageView imgSpending, imgSpent;
    private int screentWidth;
    private int screentHeight;
    private int maxSize;
    private int minSize;


    @Override
    protected void initData() {


        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getChildFragmentManager());
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new NearByFragment());

        homeViewPagerAdapter.setFragments(fragments);

        viewPagerHome.setAdapter(homeViewPagerAdapter);

        tabHome.setupWithViewPager(viewPagerHome);

//        tabHome.setTabGravity(TabLayout.GRAVITY_FILL);
//        tabHome.setTabMode(TabLayout.MODE_FIXED);

        homePresenter = new HomePresenter(this);
        homePresenter.startRequest(URLValues.HOME_HOT_MARKET,HomeBean.class);


        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        screentWidth = metric.widthPixels; // 屏幕宽度（像素）
        screentHeight = metric.heightPixels;
        measureSize(screentHeight);

    }


    private void measureSize(int layoutSize) {
        int halfWidth = layoutSize / 2;
        maxSize = halfWidth - 50;
//        minSize = (layoutSize - maxSize) / (mainContain.getChildCount() - 1);


    }

    @Override
    protected int setContentView() {

        return R.layout.home_fragment;

    }

    @Override
    protected void initViews() {
        recyviewHome = findView(R.id.recyview_home);
        viewPagerHome = findView(R.id.viewpager_home);
        toolbarHome = findView(R.id.toolbar_home);
        tabHome = findView(R.id.tab_home);
        nestscrollHome = findView(R.id.nestscroll_home);
//        tab1Home = findView(R.id.tab1_home);
        homeAppbar = findView(R.id.recyview_appbar);
        rl = findView(R.id.rl_home);
        rl1 = findView(R.id.rl1_home);
        rl0 = findView(R.id.rl0_home);
        tvSpending = findView(R.id.tv_not_spending);
        imgSpending = findView(R.id.img_not_spending);
        tvSpent = findView(R.id.tv_yet_spent);
        imgSpent = findView(R.id.img_yet_spent);


    }

    @Override
    protected void initListeners() {
        tvSpending.setOnClickListener(this);
        imgSpending.setOnClickListener(this);
        tvSpent.setOnClickListener(this);
        imgSpent.setOnClickListener(this);
        recyviewHome.setLoadingMoreEnabled(false);
        recyviewHome.setLoadingListener(new MyRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        homePresenter.startRequest(URLValues.HOME_HOT_MARKET,HomeBean.class);
                        recyviewHome.refreshComplete();
                        toolbarHome.setVisibility(View.VISIBLE);
                    }

                }, 1000);
            }
            @Override
            public void onLoadMore() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        recyviewHome.loadMoreComplete();
                    }
                }, 1000);
            }
        });

        toolbarHome.setOnScrollStateListener(this);
        toolbarHome.setOffset(600);
        toolbarHome.setBgColor(getResources().getColor(R.color.toolbar_home_color));
        viewPagerHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                nestscrollHome.scrollTo(0, 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        homeAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {


            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                toolbarHome.setChangeTop(-verticalOffset);
                Log.d("HomeFragment", "appBarLayout.getTop():" + appBarLayout.getTop());
                if (-verticalOffset == appBarLayout.getHeight() - tabHome.getHeight() - statusBarHeight) {


                    rl0.setVisibility(View.INVISIBLE);
                    rl1.setVisibility(View.VISIBLE);

//
//                    StretchAnimation stretchanimation = new StretchAnimation(maxSize, minSize, StretchAnimation.TYPE.horizontal, 500);
//
//                    stretchanimation.setInterpolator(new BounceInterpolator());
//                    stretchanimation.setDuration(800);
//                    stretchanimation.setOnAnimationListener(HomeFragment.this);
//
//                    stretchanimation.startAnimation(rl0);


                } else {

                    rl0.setVisibility(View.VISIBLE);
//                    rl1.setVisibility(View.INVISIBLE);
                    rl1.setVisibility(View.INVISIBLE);
//                    tabHome.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#FF5644"));
//                    tabHome.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                    tabHome.setSelectedTabIndicatorColor(Color.parseColor("#FF5644"));


                }
            }
        });


        viewPagerHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                switch (position) {
                    case 0:


                        tvSpending.setTextColor(Color.parseColor("#FFFFFF"));
                        tvSpent.setTextColor(Color.parseColor("#FF9A8F"));

                        imgSpending.setBackgroundResource(R.drawable.tab_shape);
                        imgSpent.setBackgroundResource(R.drawable.tab2_shape);
                        break;
                    case 1:
                        tvSpending.setTextColor(Color.parseColor("#FF9A8F"));
                        tvSpent.setTextColor(Color.parseColor("#FFFFFF"));
                        imgSpent.setBackgroundResource(R.drawable.tab_shape);
                        imgSpending.setBackgroundResource(R.drawable.tab2_shape);


                        break;

                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
    public void onResponse(Object o) {


        HomeBean homeBean = (HomeBean) o;
        ArrayList<HomeBeanHot> arrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                HomeBeanHot homeBeanHot = new HomeBeanHot();
                homeBeanHot.setImageUrl(homeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).getImageUrl());
//                homeBeanHot.setGoOperation(homeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).getGoOperation());
                arrayList.add(homeBeanHot);
            }
        }


        homeAdapter = new HomeAdapter(context, arrayList);

        setOnItemClick();

        recyviewHome.setLayoutManager(new GridLayoutManager(context, 3));

        recyviewHome.setAdapter(homeAdapter);

        recyviewHome.setRefreshProgressStyle(MyRecyclerView.ProgressStyle.BallSpinFadeLoader);

        recyviewHome.setArrowImageView(R.drawable.selena);

    }

    @Override
    public void onError() {

        Toast.makeText(AroundAPP.getContext(), "网络不可用", Toast.LENGTH_SHORT).show();

    }


    private void setOnItemClick() {
        homeAdapter.setOnItemClick(new MyRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, int position) {

                Toast.makeText(context, "position:" + position, Toast.LENGTH_SHORT).show();


            }
        });
    }


    @Override
    public void updateFraction(float fraction) {

    }

    @Override
    public void animationEnd(View v) {

    }


    public void setStatusBarHeight(int statusBarHeight) {

        this.statusBarHeight = statusBarHeight;

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rl.getLayoutParams();
        params.setMargins(50, statusBarHeight + 10, 50, 20);// 通过自定义坐标来放置你的控件
        rl.setLayoutParams(params);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_not_spending:
                tvSpending.setTextColor(Color.parseColor("#FFFFFF"));
                tvSpent.setTextColor(Color.parseColor("#FF9A8F"));

                imgSpending.setBackgroundResource(R.drawable.tab_shape);
                imgSpent.setBackgroundResource(R.drawable.tab2_shape);
                viewPagerHome.setCurrentItem(0);
                break;
            case R.id.tv_yet_spent:
                tvSpending.setTextColor(Color.parseColor("#FF9A8F"));
                tvSpent.setTextColor(Color.parseColor("#FFFFFF"));
                imgSpent.setBackgroundResource(R.drawable.tab_shape);
                imgSpending.setBackgroundResource(R.drawable.tab2_shape);
                viewPagerHome.setCurrentItem(1);

                break;


        }
    }
}
