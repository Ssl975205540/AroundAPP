package lanou.around.home;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.app.AroundAPP;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.bean.EventBean;
import lanou.around.bean.HomeBean;
import lanou.around.bean.HomeBeanHot;
import lanou.around.home.nearby.NearByFragment;
import lanou.around.home.recommend.RecommendFragment;
import lanou.around.presenter.HomePresenter;
import lanou.around.tools.http.URLValues;
import lanou.around.tools.recycle.RevealToolbar;
import lanou.around.widget.MorphFrameLayout;
import lanou.around.widget.MyRecyclerView;
import lanou.around.widget.RoundImageView;
import lanou.around.widget.StretchAnimation;
import lanou.around.widget.TransparentToolBar;


/**
 * Created by dllo on 16/10/22.
 */

public class HomeFragment extends BaseFragment implements InterView, TransparentToolBar.OnScrollStateListener, StretchAnimation.AnimationListener, View.OnClickListener, OnItemClickListener {

    private MyRecyclerView recyviewHome;
    private ViewPager viewPagerHome;
    private TabLayout tabHome;
    private HomeAdapter homeAdapter;
    private HomePresenter homePresenter;
    private AppBarLayout homeAppbar;
    private TransparentToolBar toolbarHome;
    private NestedScrollView nestscrollHome;
    private int statusBarHeight;
    private MorphFrameLayout rl;
    private RelativeLayout rl1, rl0;
    private TextView tvSpending, tvSpent;
    private ImageView imgSpending, imgSpent , icon , friendIcon , friendCreame , friendPakge;
    private boolean refresh = false;
    private RoundImageView circle_search_home;
    private ConvenientBanner bannerHome;
    private LinearLayout supplementary, supplement , hsvLinear , friendLinear;
    private FrameLayout fffff;
    private NestedScrollView nestscroll;



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
        homePresenter.startRequest(URLValues.HOME_HOT_MARKET, HomeBean.class);




    }


    @Override
    protected int setContentView() {

        return R.layout.home_fragment;

    }

    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);
        recyviewHome = findView(R.id.recyview_home);
        viewPagerHome = findView(R.id.viewpager_home);
        toolbarHome = findView(R.id.toolbar_home);
        tabHome = findView(R.id.tab_home);


        fffff = findView(R.id.fffff);
        supplement = findView(R.id.supplement);

        supplementary = findView(R.id.supplementary);

        homeAppbar = findView(R.id.recyview_appbar);
        rl = findView(R.id.rl_home);
        rl1 = findView(R.id.rl1_home);
        rl0 = findView(R.id.rl0_home);
        tvSpending = findView(R.id.tv_not_spending);
        imgSpending = findView(R.id.img_not_spending);
        tvSpent = findView(R.id.tv_yet_spent);
        imgSpent = findView(R.id.img_yet_spent);
        circle_search_home = findView(R.id.circle_search_home);

        View view = LayoutInflater.from(context).inflate(R.layout.home_recly_header, null);
        hsvLinear = (LinearLayout) view.findViewById(R.id.hsv_ll_home);
        friendLinear = (LinearLayout) view.findViewById(R.id.friend_ll);

        friendCreame = (ImageView) view.findViewById(R.id.your_friend_creame);
        friendPakge = (ImageView) view.findViewById(R.id.your_friend_pakge);
        friendIcon = (ImageView) view.findViewById(R.id.your_friend_image);
        friendIcon.setImageResource(R.mipmap.vn);
        Picasso.with(context).load(URLValues.HOME_FRIEND_PAKGE).into(friendCreame);
        Picasso.with(context).load(URLValues.HOME_FRIEND_CREAME).into(friendPakge);


        recyviewHome.addHeaderView(view);
        bannerHome = findView(view, R.id.banner_home);


    }

    @Subscribe
    public void setEvent(EventBean event){


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initListeners() {
        tvSpending.setOnClickListener(this);
        imgSpending.setOnClickListener(this);
        tvSpent.setOnClickListener(this);
        imgSpent.setOnClickListener(this);
        friendLinear.setOnClickListener(this);
        recyviewHome.setLoadingMoreEnabled(false);

        recyviewHome.setLoadingListener(new MyRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {

                        if(refresh == true){
                            homePresenter.startRequest(URLValues.HOME_HOT_MARKET, HomeBean.class);
                        }else {
                            refresh = true;
                        }


                    }
                }, 3000);

            }

            @Override
            public void setdisplay(int i) {
                switch (i) {

                    case 0:
                        toolbarHome.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        toolbarHome.setVisibility(View.GONE);
                        break;
                }
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
        toolbarHome.setOffset(360);
        toolbarHome.setBgColor(getResources().getColor(R.color.toolbar_home_color));
        viewPagerHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        homeAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {




            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                toolbarHome.setChangeTop(-verticalOffset);
                Log.d("HomeFragment", "-verticalOffset:" + -verticalOffset);
                Log.d("HomeFragment", "appBarLayout.getHeight() - tabHome.getHeight() - statusBarHeight:" + (appBarLayout.getHeight() - tabHome.getHeight() - statusBarHeight));
                if (-verticalOffset == appBarLayout.getHeight() - tabHome.getHeight() - statusBarHeight) {


                        rl1.setVisibility(View.VISIBLE);
                        RevealToolbar.HideReveal(rl0);




                } else {


                    rl1.setVisibility(View.INVISIBLE);
                    rl0.setVisibility(View.VISIBLE);

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

        recyviewHome.setRefreshing(true);
        toolbarHome.setVisibility(View.GONE);
    }

    @Override
    public void stopAnimation() {

        recyviewHome.refreshComplete();
        toolbarHome.setVisibility(View.VISIBLE);
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
        for (int i = 0 ; i < 8 ; i++) {
            View hsv_item = LayoutInflater.from(context).inflate(R.layout.hsv_item , null);
            icon = (ImageView) hsv_item.findViewById(R.id.hsv_item_image);
            Picasso.with(context).load(homeBean.getRespData().getLowBanners().get(i).getImageUrl()).into(icon);
            final String clickUrl = homeBean.getRespData().getLowBanners().get(i).getGoUrl();
            final String clickTitle = homeBean.getRespData().getLowBanners().get(i).getPostName();
            final String hsv_click = URLValues.HOME_HOT_MARKET;
            if (!TextUtils.isEmpty(hsv_click)) {
                hsv_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(hsv_click);
                        Intent intent = new Intent(AroundAPP.getContext() , HSVClickActivity.class);
                        intent.putExtra("url" , clickUrl);
                        intent.putExtra("title" , clickTitle);
                        getActivity().startActivity(intent);
                    }
                });
            }

            hsvLinear.addView(hsv_item);
        }


        homeAdapter = new HomeAdapter(context, arrayList);

        setOnItemClick();

        recyviewHome.setLayoutManager(new GridLayoutManager(context, 3));

        recyviewHome.setAdapter(homeAdapter);

        recyviewHome.setRefreshProgressStyle(MyRecyclerView.ProgressStyle.BallSpinFadeLoader);

        ArrayList<String> arrayList1 = new ArrayList<>();

        for (int i = 0; i < homeBean.getRespData().getTopBanners().size(); i++) {
            arrayList1.add(homeBean.getRespData().getTopBanners().get(i).getImageUrl());
        }

        bannerHome.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, arrayList1)
//                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                .setPageIndicator(new int[]{R.drawable.dot_selected_f, R.drawable.dot_unselected_f})
//                //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)//监听翻页事件
                .setOnItemClickListener(this).startTurning(4000);


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
        Log.d("HomeFragment", "statusBarHeight:" + statusBarHeight);



        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rl.getLayoutParams();
        params.setMargins(50, statusBarHeight + 20, 50, 5);// 通过自定义坐标来放置你的控件
        rl.setLayoutParams(params);
        toolbarHome.getLayoutParams().height = statusBarHeight + tabHome.getHeight();
        supplementary.getLayoutParams().height = statusBarHeight / 2;
        supplement.getLayoutParams().height = statusBarHeight / 2;


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
            case R.id.friend_ll:
               Intent intent  = new Intent(AroundAPP.getContext() , FriendActivity.class);
                getActivity().startActivity(intent);

                break;


        }
    }

    @Override
    public void onItemClick(int position) {


    }
}