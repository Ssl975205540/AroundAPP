package lanou.around.home.nearby;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/10/29.
 */
public class HomeTabAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private List<String> strings;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
        notifyDataSetChanged();
    }

    public HomeTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if(position > strings.size()-1){
            return super.getPageTitle(position);
        }
        return strings.get(position);
    }
}
