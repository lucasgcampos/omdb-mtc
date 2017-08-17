package pao.de.queijo.omdbmtc.ui.activity;

import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import io.reactivex.functions.Action;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class BaseActivity extends AppCompatActivity {

    protected void setUpToolbar(Toolbar toolbar, String title, int navigationIconId, Action action) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(title);
        }

        if (navigationIconId != 0) {
            toolbar.setNavigationIcon(VectorDrawableCompat.create(getResources(), navigationIconId, getTheme()));

            if (action != null) {
                toolbar.setNavigationOnClickListener(v -> {
                    try {
                        action.run();
                    } catch (Exception e) {

                    }
                });
            } else {
                toolbar.setNavigationOnClickListener(v -> finish());
            }
        }
    }

    protected void setUpToolbar(Toolbar toolbar, String title) {
        setUpToolbar(toolbar, title, 0, null);
    }

}
