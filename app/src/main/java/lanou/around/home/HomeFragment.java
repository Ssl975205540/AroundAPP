package lanou.around.home;

import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.bean.HomeBean;
import lanou.around.bean.HomeBeanHot;
import lanou.around.home.nearby.NearByFragment;
import lanou.around.home.recommend.RecommendFragment;
import lanou.around.presenter.HomePresenter;
import lanou.around.tools.recycle.MyRecyclerView;
import lanou.around.tools.recycle.http.URLValues;

/**
 * Created by dllo on 16/10/22.
 */

public class HomeFragment extends BaseFragment implements InterView<HomeBean> {

    private MyRecyclerView recyviewHome;
    private ViewPager viewPagerHome;
    private TabLayout tabHome;
    private HomeAdapter homeAdapter;
    private HomePresenter homePresenter;

    @Override
    protected void initData() {


        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getChildFragmentManager());
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new NearByFragment());

        fragments.add(new RecommendFragment());

        homeViewPagerAdapter.setFragments(fragments);

        viewPagerHome.setAdapter(homeViewPagerAdapter);

        tabHome.setupWithViewPager(viewPagerHome);
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
        tabHome = findView(R.id.tab_home);

    }

    @Override
    protected void initListeners() {

        recyviewHome.setLoadingMoreEnabled(false);
        recyviewHome.setLoadingListener(new MyRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable(){
                    public void run() {

                        homePresenter.startRequest(URLValues.HOME_HOT_MARKET);

                        recyviewHome.refreshComplete();
                    }

                }, 1000);
            }

            @Override
            public void onLoadMore() {

                    new Handler().postDelayed(new Runnable(){
                        public void run() {


                            recyviewHome.loadMoreComplete();

                        }
                    }, 1000);
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
                homeBeanHot.setGoOperation(homeBean.getRespData().getActBanners().get(0).getMiddleBanner().getBanners().get(i).get(j).getGoOperation());
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

        ArrayList<HomeBeanHot> arrayList = new ArrayList<>();
        homeAdapter = new HomeAdapter(context, arrayList);

        setOnItemClick();

        recyviewHome.setLayoutManager(new GridLayoutManager(context, 3));

        recyviewHome.setAdapter(homeAdapter);

        recyviewHome.setRefreshProgressStyle(MyRecyclerView.ProgressStyle.BallSpinFadeLoader);

        recyviewHome.setArrowImageView(R.drawable.selena);
    }



    private void setOnItemClick() {
        homeAdapter.setOnItemClick(new MyRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, int position) {

                Toast.makeText(context, "position:" + position, Toast.LENGTH_SHORT).show();


            }
        });
    }


}
