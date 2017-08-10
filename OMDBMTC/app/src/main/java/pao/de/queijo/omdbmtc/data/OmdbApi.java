package pao.de.queijo.omdbmtc.data;

import io.reactivex.Observable;
import pao.de.queijo.omdbmtc.data.model.Movie;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */
public interface OmdbApi {


    @GET("?t=${name}")
    Observable<Movie> getMovieByName(@Path("name") String name);

    @GET("?t=${name}&y=${year}")
    Observable<Movie> getMovieByNameAndYear(@Path("name") String name, @Path("year") int year);

}
