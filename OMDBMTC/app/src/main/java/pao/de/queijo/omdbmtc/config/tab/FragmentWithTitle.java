package pao.de.queijo.omdbmtc.config.tab;

import android.support.v4.app.Fragment;

/**
 * Data structure to {@link DefaultViewPagerAdapter}
 *
 * @author Lucas Campos
 * @since 1.0.0
 */
public class FragmentWithTitle {
    private Fragment fragment;
    private String title;

    public FragmentWithTitle(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public String getTitle() {
        return title;
    }
}
