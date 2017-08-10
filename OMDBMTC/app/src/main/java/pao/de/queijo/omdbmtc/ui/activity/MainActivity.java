package pao.de.queijo.omdbmtc.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import pao.de.queijo.omdbmtc.R;
import pao.de.queijo.omdbmtc.config.RetrofitConfig;
import pao.de.queijo.omdbmtc.data.model.Movie;
import pao.de.queijo.omdbmtc.ui.presenter.MainPresenter;
import pao.de.queijo.omdbmtc.ui.view.MainView;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */
public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;

    @BindView(R.id.title)
    EditText title;

    @BindView(R.id.year)
    EditText year;

    @BindView(R.id.search)
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this, RetrofitConfig.create("http://www.omdbapi.com/").createService());
    }

    @Override
    public void showMovieNotFound() {
        new AlertDialog.Builder(this)
                .setMessage("Filme não encontrado")
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).create()
                .show();
    }

    @Override
    public void bindResult(Movie movie) {

    }

    @OnClick(R.id.search)
    void onClickSearch() {
        if (search.isEnabled()) {
            presenter.fetch(title.getText().toString(), year.getText().toString());
        }
    }
}
