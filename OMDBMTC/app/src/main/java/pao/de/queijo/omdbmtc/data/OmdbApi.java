package pao.de.queijo.omdbmtc.data;

import io.reactivex.Observable;
import pao.de.queijo.omdbmtc.data.model.MovieDetail;
import pao.de.queijo.omdbmtc.data.model.MovieResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */
public interface OmdbApi {

    @GET("?apikey=1bcadfd2")
    Observable<MovieResponse> getMovieByName(@Query("s") String name);

    @GET("?apikey=1bcadfd2")
    Observable<MovieResponse> getMovieByNameAndYear(@Query("s") String name, @Query("y") int year);

    @GET("?apikey=1bcadfd2")
    Observable<MovieDetail> getMovieDetails(@Query("i") String movieId);

}
