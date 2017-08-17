package pao.de.queijo.omdbmtc.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import pao.de.queijo.omdbmtc.R;
import pao.de.queijo.omdbmtc.config.RetrofitConfig;
import pao.de.queijo.omdbmtc.data.model.Movie;
import pao.de.queijo.omdbmtc.data.model.MovieDetail;
import pao.de.queijo.omdbmtc.ui.presenter.MovieDetailsPresenter;
import pao.de.queijo.omdbmtc.ui.view.MovieDetailsView;

import static pao.de.queijo.omdbmtc.ui.activity.MainActivity.EXTRAS_IS_FAVORITE;
import static pao.de.queijo.omdbmtc.ui.activity.MainActivity.EXTRAS_MOVIE;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class MovieDetailsActivity extends BaseActivity implements MovieDetailsView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.subtitle)
    TextView genre;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.poster)
    ImageView poster;

    @BindView(R.id.favorite)
    ImageView favorite;

    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;

    private Unbinder butterKnife;
    private MovieDetailsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        butterKnife = ButterKnife.bind(this);

        Movie movie = getIntent().getParcelableExtra(EXTRAS_MOVIE);

        presenter = new MovieDetailsPresenter(this, RetrofitConfig.create("http://www.omdbapi.com/").createService(), AndroidSchedulers.mainThread());
        presenter.fetchData(movie.getImdbID());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setUpToolbar(toolbar, "Título desta tela", R.drawable.ic_back_arrow, this::onBackPressed);
    }

    @Override
    public void bind(MovieDetail detail) {
        title.setText(detail.getTitle());
        genre.setText(detail.getGenre());
        description.setText(detail.getPlot());

        if (isFavorite()) {
            favorite.setImageResource(R.drawable.ic_favorite);
        } else {
            favorite.setImageResource(R.drawable.ic_favorite_border);
        }

        Picasso.with(this).load(detail.getPoster()).fit().into(poster);
    }

    @Override
    public void flipLoader() {
        viewFlipper.setDisplayedChild(1);
    }

    @Override
    public void flipContent() {
        viewFlipper.setDisplayedChild(0);
    }

    @Override
    public void showWireNotFound() {
        new AlertDialog.Builder(this)
                .setMessage("Conecte-se a uma rede (:")
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).create()
                .show();
    }

    @Override
    public void showSomethingWrongHappen() {
        new AlertDialog.Builder(this)
                .setMessage("Alguma coisa errada não está certa. Whaaaat?")
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).create()
                .show();
    }

    @Override
    public void onBackPressed() {
        Movie movie = getIntent().getParcelableExtra(EXTRAS_MOVIE);

        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRAS_MOVIE, movie);
        intent.putExtra(EXTRAS_IS_FAVORITE, isFavorite());

        setResult(RESULT_OK, intent);
        finish();
    }

    @OnClick(R.id.favorite)
    void onClickFavorite() {
        if (isFavorite()) {
            getIntent().putExtra(EXTRAS_IS_FAVORITE, false);
            favorite.setImageResource(R.drawable.ic_favorite_border);
        } else {
            getIntent().putExtra(EXTRAS_IS_FAVORITE, true);
            favorite.setImageResource(R.drawable.ic_favorite);
        }
    }

    private boolean isFavorite() {
        return getIntent().getBooleanExtra(EXTRAS_IS_FAVORITE, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (butterKnife != null) {
            butterKnife.unbind();
        }
    }
}
