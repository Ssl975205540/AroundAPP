package lanou.around.home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;

import java.util.ArrayList;

import lanou.around.R;
import lanou.around.base.BaseFragment;
import lanou.around.bean.HomeBean;
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
        }

        HomeAdapter homeAdapter = new HomeAdapter(context, arrayList);

        recyviewHome.setAdapter(homeAdapter);

        recyviewHome.setLayoutManager(new GridLayoutManager(context, 3));

        recyviewHome.setLoadingMoreEnabled(false);





    }


}
