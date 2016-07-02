package com.toan_it.library.library.injector.module;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.toan_it.library.BuildConfig;
import com.toan_it.library.library.data.networking.RestApi;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by toan.it
 * Date: 28/05/2016
 */
@Module
public class NetworkModule {
    private File mCacheFile;
    private boolean isConnected;
    public NetworkModule(@NonNull File cacheFile, @NonNull boolean checkConnect) {
        this.mCacheFile = cacheFile;
        this.isConnected=checkConnect;
    }
    @Singleton
    @NonNull
    @Provides
    RestApi sRestClient(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }
    @Singleton
    @NonNull
    @Provides
    Retrofit sRetrofit(@NonNull OkHttpClient okHttpClient,@NonNull Gson gson,@NonNull RxJavaCallAdapterFactory rxJavaCallAdapter) {
        return new Retrofit.Builder()
                .baseUrl(RestApi.BaoOnline)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJavaCallAdapter)
                .build();

    }
    @Singleton
    @NonNull
    @Provides
    OkHttpClient makeOkHttpClient(@NonNull HttpLoggingInterceptor httpLoggingInterceptor,@NonNull Interceptor interceptor) {
        Cache cache = null;
        try {
            cache = new Cache(mCacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }
    @Singleton
    @NonNull
    @Provides
    Gson providesGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.create();
    }
    @Singleton
    @NonNull
    @Provides
    RxJavaCallAdapterFactory providesRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
    }
    @Singleton
    @NonNull
    @Provides
    HttpLoggingInterceptor makeLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }
    @Singleton
    @NonNull
    @Provides
    Interceptor cache(){
        return chain -> {
            Request.Builder r = chain.request().newBuilder();
            if (isConnected) {
                int maxAge = 60;
                r.addHeader("Cache-Control", "public, max-age=" + maxAge);
            } else {
                int maxStale = 30 * 24 * 60 * 60; // 30 days
                r.addHeader("cache-control", "public, only-if-cached, max-stale=" + maxStale);
            }
            return chain.proceed(r.build());
        };
    }
}
