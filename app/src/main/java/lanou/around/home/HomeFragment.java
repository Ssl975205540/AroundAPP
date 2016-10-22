package lanou.around.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseFragment;
import lanou.around.bean.HomeBean;
import lanou.around.home.nearby.NearByFragment;
import lanou.around.home.recommend.RecommendFragment;
import lanou.around.tools.recycle.MyRecyclerView;

/**
 * Created by dllo on 16/10/22.
 */

public class HomeFragment extends BaseFragment {

    private MyRecyclerView recyviewHome;
    private ViewPager viewPagerHome;
    private TabLayout tabHome;

    @Override
    protected void initData() {


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

        ArrayList<HomeBean> arrayList = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setAnInt(R.mipmap.s3);
            arrayList.add(homeBean);
        }

        HomeAdapter homeAdapter = new HomeAdapter(context, arrayList);
        recyviewHome.setLayoutManager(new GridLayoutManager(context, 3));

        recyviewHome.setAdapter(homeAdapter);

        recyviewHome.setRefreshProgressStyle(MyRecyclerView.ProgressStyle.BallSpinFadeLoader);

        recyviewHome.setArrowImageView(R.drawable.selena);

        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getChildFragmentManager());

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new NearByFragment());

        fragments.add(new RecommendFragment());

        homeViewPagerAdapter.setFragments(fragments);

        viewPagerHome.setAdapter(homeViewPagerAdapter);

        tabHome.setupWithViewPager(viewPagerHome);

//        mRecyclerView.setLoadingListener(new MyRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//                refreshTime ++;
//                times = 0;
//                new Handler().postDelayed(new Runnable(){
//                    public void run() {
//
//                        listData.clear();
//                        for(int i = 0; i < 15 ;i++){
//                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
//                        }
//                        mAdapter.notifyDataSetChanged();
//                        mRecyclerView.refreshComplete();
//                    }
//
//                }, 1000);            //refresh data here
//            }
//
//            @Override
//            public void onLoadMore() {
//                if(times < 2){
//                    new Handler().postDelayed(new Runnable(){
//                        public void run() {
//                            for(int i = 0; i < 15 ;i++){
//                                listData.add("item" + (1 + listData.size() ) );
//                            }
//                            mRecyclerView.loadMoreComplete();
//                            mAdapter.notifyDataSetChanged();
//                        }
//                    }, 1000);
//                } else {
//                    new Handler().postDelayed(new Runnable() {
//                        public void run() {
//                            for(int i = 0; i < 9 ;i++){
//                                listData.add("item" + (1 + listData.size() ) );
//                            }
//                            mRecyclerView.setNoMore(true);
//                            mAdapter.notifyDataSetChanged();
//                        }
//                    }, 1000);
//                }
//                times ++;
//            }
//        });
    }


}
