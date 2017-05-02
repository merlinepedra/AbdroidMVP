package com.abdymalikmulky.abdroidmvp.app.data.news;

import com.abdymalikmulky.abdroidmvp.app.data.news.pojo.Berita;
import com.abdymalikmulky.abdroidmvp.app.data.news.pojo.Beritas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Created by abdymalikmulky on 1/20/17.
 */

public class NewsRemote implements NewsDataSource {
    private Retrofit retrofit;

    public NewsRemote(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void load(int page, final LoadNewsCallback callback) {

        NewsApi api = retrofit.create(NewsApi.class);
        Call<Beritas> call = api.getList(page);
        call.enqueue(new Callback<Beritas>() {
            @Override
            public void onResponse(Call<Beritas> call, Response<Beritas> response) {
                List<Berita> news = response.body().getBerita();
                callback.onNewsLoaded(news);
            }

            @Override
            public void onFailure(Call<Beritas> call, Throwable t) {
                Timber.e(t);
            }
        });
    }

    @Override
    public void get(Berita news, GetNewsCallback callback) {

    }

    @Override
    public void save(Berita news) {

    }

    @Override
    public void delete(Berita news) {

    }
}