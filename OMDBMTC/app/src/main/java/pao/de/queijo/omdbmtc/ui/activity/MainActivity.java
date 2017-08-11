package pao.de.queijo.omdbmtc.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pao.de.queijo.omdbmtc.R;
import pao.de.queijo.omdbmtc.config.tab.DefaultViewPagerAdapter;
import pao.de.queijo.omdbmtc.config.tab.FragmentWithTitle;
import pao.de.queijo.omdbmtc.data.model.Movie;
import pao.de.queijo.omdbmtc.ui.fragment.FavoriteFragment;
import pao.de.queijo.omdbmtc.ui.fragment.SearchMovieFragment;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.view_pager_tab)
    ViewPager viewPager;

    private List<Movie> favorites = new ArrayList<>();

    public List<Movie> getFavorites() {
        return favorites;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpViewPager();
        setUpTabLayout();
    }

    private void setUpViewPager() {
        DefaultViewPagerAdapter adapter = new DefaultViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentWithTitle(new SearchMovieFragment(), "Lista filmes"));
        adapter.addFragment(new FragmentWithTitle(new FavoriteFragment(), "Favoritos"));

        viewPager.setAdapter(adapter);
    }

    private void setUpTabLayout() {
        tabLayout.setupWithViewPager(viewPager);
    }



}
