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
import android.util.Log;
import android.view.View;
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

public class HomeFragment extends BaseFragment implements InterView<HomeBean>, TransparentToolBar.OnScrollStateListener,StretchAnimation.AnimationListener {

    private MyRecyclerView recyviewHome;
    private WrapContentHeightViewPager viewPagerHome;
    private TabLayout tabHome;
    private HomeAdapter homeAdapter;
    private int d;
    private HomePresenter homePresenter;
    private AppBarLayout homeAppbar;
    private TransparentToolBar toolbarHome;
    private NestedScrollView nestscrollHome;


    @Override
    protected void initData() {


        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getChildFragmentManager());
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new NearByFragment());

        fragments.add(new RecommendFragment());

        homeViewPagerAdapter.setFragments(fragments);

        viewPagerHome.setAdapter(homeViewPagerAdapter);

        tabHome.setupWithViewPager(viewPagerHome);

//        tabHome.setTabGravity(TabLayout.GRAVITY_FILL);
//        tabHome.setTabMode(TabLayout.MODE_FIXED);

        homePresenter = new HomePresenter(this);
        homePresenter.startRequest(URLValues.HOME_HOT_MARKET);

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

    }

    @Override
    protected void initListeners() {

        recyviewHome.setLoadingMoreEnabled(false);
        recyviewHome.setLoadingListener(new MyRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        homePresenter.startRequest(URLValues.HOME_HOT_MARKET);

                        recyviewHome.refreshComplete();
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


                Log.d("HomeFragment", "toolbarHome.getWidth():" + toolbarHome.getWidth());
                Log.d("HomeFragment", "toolbarHome.getHeight():" + toolbarHome.getHeight());

                int c = appBarLayout.getHeight() - tabHome.getHeight();
                if (-verticalOffset == appBarLayout.getHeight() - tabHome.getHeight()) {


                    toolbarHome.setVisibility(View.GONE);

                    tabHome.setTabTextColors(Color.parseColor("#FFACA2"), Color.parseColor("#ffffff"));
                    tabHome.setBackgroundColor(Color.parseColor("#FF5644"));
                    tabHome.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));
//                    StretchAnimation stretchanimation = new StretchAnimation(768, 100, StretchAnimation.TYPE.horizontal, 500);
//// 设置它的插值器 弹球效果
//                    stretchanimation.setInterpolator(new BounceInterpolator());
//// 动画播放的总时间
//// 动画播放完后的回调
////                    stretchanimation.setOnAnimationListener(this);
//// 播放动画 参数是你要播放的View
//                    stretchanimation.startAnimation(toolbarHome);



                } else {
                    toolbarHome.setVisibility(View.VISIBLE);

                    tabHome.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#FF5644"));
                    tabHome.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    tabHome.setSelectedTabIndicatorColor(Color.parseColor("#FF5644"));



                }
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
    public void onResponse(HomeBean homeBean) {


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
}
