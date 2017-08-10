package pao.de.queijo.omdbmtc.config;

import io.reactivex.schedulers.Schedulers;
import pao.de.queijo.omdbmtc.data.OmdbApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class RetrofitConfig {

    private static Retrofit retrofit;

    public static RetrofitConfig create(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

        return new RetrofitConfig();
    }

    public OmdbApi createService() {
        return retrofit.create(OmdbApi.class);
    }

}
