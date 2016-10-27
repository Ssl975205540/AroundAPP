package lanou.around.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/22.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private final ArrayList<String> arrayList;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);

        arrayList = new ArrayList<>();
        arrayList.add("推荐");
        arrayList.add("附近");


    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);

    }

    @Override
    public int getCount() {
        return fragments.size();

    }


    @Override
    public CharSequence getPageTitle(int position) {
        return arrayList.get(position);
    }
}
