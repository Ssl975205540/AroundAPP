package lanou.around.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/10/22.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);

    }

    @Override
    public int getCount() {
        return fragments.size();

    }
}
