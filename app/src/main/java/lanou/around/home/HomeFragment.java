package lanou.around.home;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import lanou.around.R;
import lanou.around.app.AroundAPP;
import lanou.around.aroundinterface.InterToolBar;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.classification.seek.SeekActivity;
import lanou.around.tools.util.IntentUtils;
import lanou.around.bean.EventBean;
import lanou.around.bean.HomeBean;
import lanou.around.bean.HomeBeanHot;
import lanou.around.classification.classifyview.DigitWebActivity;
import lanou.around.classification.search.SearchActivity;
import lanou.around.home.nearby.NearByFragment;
import lanou.around.home.recommend.RecommendFragment;
import lanou.around.presenter.HomePresenter;
import lanou.around.tools.http.URLValues;
import lanou.around.widget.MyRecyclerView;
import lanou.around.widget.SellAnall;
import lanou.around.widget.StickyNavLayout;
import lanou.around.widget.StretchAnimation;
import lanou.around.widget.TransparentToolBar;

import static lanou.around.home.HSVClickActivity.TITLE;
import static lanou.around.home.HSVClickActivity.URL;


/**
 * Created by dllo on 16/10/22.
 */

public class HomeFragment extends BaseFragment implements InterView, TransparentToolBar.OnScrollStateListener, StretchAnimation.AnimationListener, View.OnClickListener, OnItemClickListener {

    private MyRecyclerView recyviewHome;
    private ViewPager viewPagerHome;
    private TabLayout tabHome;
    private HomeAdapter homeAdapter;
    private HomePresenter homePresenter;
    //    private AppBarLayout homeAppbar;
    private TransparentToolBar toolbarHome;
    private NestedScrollView nestscrollHome;
    private int statusBarHeight;
    private RelativeLayout rl;
    private RelativeLayout rl1;
    private LinearLayout rl0;
    private TextView tvSpending, tvSpent;
    private ImageView imgSpending, imgSpent, friendIcon, friendCreame, friendPakge;
    private boolean refresh = false;
    private ImageView circle_search_home;
    private ConvenientBanner bannerHome;
    private LinearLayout supplementary, supplement, hsvLinear, friendLinear;

    private StickyNavLayout mainContent;
    private HomeBean mHomeBean;
    private ArrayList<HomeBeanHot> mArrayList;
    private SellAnall mSv1;


    @Override
    protected void initData() {


        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getChildFragmentManager());
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new NearByFragment());

        homeViewPagerAdapter.setFragments(fragments);


        viewPagerHome.setAdapter(homeViewPagerAdapter);

        tabHome.setupWithViewPager(viewPagerHome);


        homePresenter = new HomePresenter(this);
        homePresenter.startRequest(URLValues.HOME_HOT_MARKET, HomeBean.class);

        ShareSDK.initSDK(context, "sharesdk的appkey");
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

        mainContent = findView(R.id.main_content);


        supplement = findView(R.id.supplement);

        supplementary = findView(R.id.supplementary);

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

        mSv1 = findView(R.id.sv_1);


        friendCreame = (ImageView) view.findViewById(R.id.your_friend_creame);
        friendPakge = (ImageView) view.findViewById(R.id.your_friend_pakge);
        friendIcon = (ImageView) view.findViewById(R.id.your_friend_image);
        friendIcon.setImageResource(R.mipmap.vn);
        Picasso.with(context).load(URLValues.HOME_FRIEND_PAKGE).into(friendCreame);
        Picasso.with(context).load(URLValues.HOME_FRIEND_CREAME).into(friendPakge);
        recyviewHome.addHeaderView(view);
        bannerHome = findView(view, R.id.banner_home);

        setview();

    }

    @Subscribe
    public void setEvent(EventBean event) {


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initListeners() {
        circle_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        tvSpending.setOnClickListener(this);
        imgSpending.setOnClickListener(this);
        tvSpent.setOnClickListener(this);
        imgSpent.setOnClickListener(this);
        toolbarHome.setOnClickListener(this);
        friendLinear.setOnClickListener(this);
        recyviewHome.setLoadingMoreEnabled(false);
        mainContent.setToolbar(new InterToolBar() {
            public boolean isAnim = false;

            @Override
            public void setTools(int t) {
                toolbarHome.setChangeTop(t);

                if (t == mainContent.getHeight() - tabHome.getHeight() - viewPagerHome.getHeight()) {
                    mSv1.startMoveAnim();
                    circle_search_home.setVisibility(View.VISIBLE);
                    rl1.setVisibility(View.VISIBLE);
                    rl0.setVisibility(View.INVISIBLE);
                    isAnim = true;


                } else {

                    if (isAnim) {
                        isAnim = false;
                        mSv1.startRotateAnim();
                        mSv1.setBolll(new InterToolBar() {
                            @Override
                            public void setTools(int t) {
                                rl1.setVisibility(View.INVISIBLE);
                                rl0.setVisibility(View.VISIBLE);
                                circle_search_home.setVisibility(View.INVISIBLE);

                            }
                        });


                    }


                }
            }
        });


        recyviewHome.setLoadingListener(new MyRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                toolbarHome.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        if (refresh == true) {


                            homePresenter.startRequest(URLValues.HOME_HOT_MARKET, HomeBean.class);
                        } else {
                            refresh = true;
                        }
                        recyviewHome.refreshComplete();
                        toolbarHome.setVisibility(View.VISIBLE);


                    }
                }, 3000);
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
        mHomeBean = (HomeBean) o;
        mArrayList = new ArrayList<>();
        for (int i = 0; i < mHomeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().size(); i++) {
            for (int j = 0; j < mHomeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(0).size(); j++) {
                HomeBeanHot homeBeanHot = new HomeBeanHot();
                homeBeanHot.setImageUrl(mHomeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).getImageUrl());
                homeBeanHot.setPostName(mHomeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).getPostName());
                homeBeanHot.setGoOperation(mHomeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).getGoOperation());
                mArrayList.add(homeBeanHot);
            }
        }
        hsvLinear.removeAllViews();
        for (int i = 0; i < mHomeBean.getRespData().getLowBanners().size(); i++) {
            List<HomeBean.RespDataBean.LowBannersBean> lowBanners = mHomeBean.getRespData().getLowBanners();
            View hsv_item = LayoutInflater.from(context).inflate(R.layout.hsv_item, null);
            ImageView icon = (ImageView) hsv_item.findViewById(R.id.hsv_item_image);
            Glide.with(context).load(lowBanners.get(i).getImageUrl()).into(icon);
            final String clickUrl = lowBanners.get(i).getGoUrl();
            final String clickTitle = lowBanners.get(i).getPostName();
            final String hsv_click = URLValues.HOME_HOT_MARKET;
            if (!TextUtils.isEmpty(hsv_click)) {
                hsv_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(hsv_click);
                        Intent intent = new Intent(AroundAPP.getContext(), HSVClickActivity.class);
                        intent.putExtra(URL, clickUrl);
                        intent.putExtra(TITLE, clickTitle);
                        getActivity().startActivity(intent);
                    }
                });
            }
            hsvLinear.addView(hsv_item);
        }

        homeAdapter = new HomeAdapter(context, mArrayList);
        setOnItemClick();
        recyviewHome.setLayoutManager(new GridLayoutManager(context, 3));
        recyviewHome.setAdapter(homeAdapter);
        recyviewHome.setRefreshProgressStyle(MyRecyclerView.ProgressStyle.BallSpinFadeLoader);
        ArrayList<String> arrayList1 = new ArrayList<>();
        for (int i = 0; i < mHomeBean.getRespData().getTopBanners().size(); i++) {
            arrayList1.add(mHomeBean.getRespData().getTopBanners().get(i).getImageUrl());
        }

        bannerHome.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, arrayList1)

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
                Bundle bundle = new Bundle();
                bundle.putString("goUrl", mArrayList.get(position).getGoOperation().getParams().getGoUrl());
                bundle.putString("postName", mArrayList.get(position).getPostName());
//                bundle.putString("goUrl",mHomeBean.getRespData().getActBanners().get(0).
//                        getMiddleBanner().getBanners().get(1).get(position).
//                        getGoOperation().getParams().getGoUrl());
                IntentUtils.getIntents().Intent(context, DigitWebActivity.class, bundle);
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


        setview();

    }

    @Override
    public void onClick(View v) {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
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
                if (qq.isAuthValid()) {
                    friendLinear.setEnabled(qq.isAuthValid());
                } else {
                    friendLinear.setEnabled(!qq.isAuthValid());
                    Intent intent = new Intent(AroundAPP.getContext(), FriendActivity.class);
                    getActivity().startActivity(intent);
                }

                break;
            case R.id.toolbar_home:
                IntentUtils.getIntents().Intent(context, SeekActivity.class, new Bundle());
                break;
        }
    }

    @Override
    public void onItemClick(int position) {

    }


    private void setview() {

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rl.getLayoutParams();
        params.setMargins(50, statusBarHeight + 10, 50, 20);// 通过自定义坐标来放置你的控件
        rl.setLayoutParams(params);
        toolbarHome.getLayoutParams().height = statusBarHeight + tabHome.getHeight();
        supplementary.getLayoutParams().height = statusBarHeight / 2;
        supplement.getLayoutParams().height = statusBarHeight / 2;
    }
}