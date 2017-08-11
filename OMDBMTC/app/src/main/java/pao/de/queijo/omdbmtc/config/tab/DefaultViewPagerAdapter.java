package pao.de.queijo.omdbmtc.config.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class DefaultViewPagerAdapter extends FragmentPagerAdapter {
    private final List<FragmentWithTitle> fragments = new ArrayList<>();

    public DefaultViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addFragment(FragmentWithTitle fragment) {
        fragments.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
