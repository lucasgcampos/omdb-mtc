package pao.de.queijo.omdbmtc.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import pao.de.queijo.omdbmtc.R;
import pao.de.queijo.omdbmtc.config.RetrofitConfig;
import pao.de.queijo.omdbmtc.data.model.Movie;
import pao.de.queijo.omdbmtc.data.model.MovieDetail;
import pao.de.queijo.omdbmtc.ui.presenter.MovieDetailsPresenter;
import pao.de.queijo.omdbmtc.ui.view.MovieDetailsView;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsView {


    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.subtitle)
    TextView genre;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.poster)
    ImageView poster;

    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;

    private Unbinder unbind;
    private MovieDetailsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        unbind = ButterKnife.bind(this);

        presenter = new MovieDetailsPresenter(this, RetrofitConfig.create("http://www.omdbapi.com/").createService(), AndroidSchedulers.mainThread());
        Movie movie = getIntent().getParcelableExtra("data");
        presenter.fetchData(movie.getImdbID());
    }

    @Override
    public void bind(MovieDetail detail) {
        title.setText(detail.getTitle());
        genre.setText(detail.getGenre());
        description.setText(detail.getPlot());
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
}
