package lanou.around.home;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.bean.HomeBeanHot;
import lanou.around.home.nearby.NearByFragment;
import lanou.around.home.recommend.RecommendFragment;

/**
 * Created by dllo on 16/11/1.
 */

public class MyHomeAdapter extends RecyclerView.Adapter {


    private Context context;

    private ArrayList<HomeBeanHot> data;

    private Fragment fragment;

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setType(ArrayList<Integer> type) {
        this.type = type;
    }

    private ArrayList<Integer> type;

    public void setData(ArrayList<HomeBeanHot> data) {
        this.data = data;
    }

    public MyHomeAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {

        if(position < 0){
           return super.getItemViewType(position);
        }
        return type.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case 0:

                View view = LayoutInflater.from(context).inflate(R.layout.home_gird,parent,false);
                GirdHolder girdHolder = new GirdHolder(view);
                return girdHolder;


            case 1:

                View view1 = LayoutInflater.from(context).inflate(R.layout.homefragment_viewpager, parent, false);

                ViewPagerHolder viewPagerHolder = new ViewPagerHolder(view1);
                return viewPagerHolder;


        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {

            case 0:

                GirdHolder girdHolder =  (GirdHolder)holder;

                HomeAdapter homeAdapter = new HomeAdapter(context,data);
                girdHolder.home_gird_re.setAdapter(homeAdapter);
                girdHolder.home_gird_re.setLayoutManager(new GridLayoutManager(context, 3));

                break;

            case 1:


                ViewPagerHolder viewPagerHolder = (ViewPagerHolder) holder;
                HomeViewPagerAdapter homeAdapter1 = new HomeViewPagerAdapter(fragment.getChildFragmentManager());
                List<Fragment> fragments = new ArrayList<>();
                fragments.add(new RecommendFragment());
                fragments.add(new NearByFragment());
                homeAdapter1.setFragments(fragments);
                viewPagerHolder.home_viewpager.setAdapter(homeAdapter1);
                viewPagerHolder.home_tab.setupWithViewPager(viewPagerHolder.home_viewpager);


                break;


        }


    }

    @Override
    public int getItemCount() {
        return type.size();
    }


    private class ViewPagerHolder extends RecyclerView.ViewHolder {

        private final ViewPager home_viewpager;
        private final TabLayout home_tab;

        public ViewPagerHolder(View itemView) {
            super(itemView);

            home_viewpager = (ViewPager) itemView.findViewById(R.id.home_viewpager);
            home_tab = (TabLayout) itemView.findViewById(R.id.home_tab);

        }
    }

    private class GirdHolder extends RecyclerView.ViewHolder {

        private final RecyclerView home_gird_re;


        public GirdHolder(View itemView) {
            super(itemView);


            home_gird_re = (RecyclerView) itemView.findViewById(R.id.home_gird_re);


        }
    }
}
