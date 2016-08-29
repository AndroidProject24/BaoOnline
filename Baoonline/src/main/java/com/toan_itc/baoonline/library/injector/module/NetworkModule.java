package com.toan_itc.baoonline.library.injector.module;

import android.support.annotation.NonNull;

import com.toan_it.library.BuildConfig;
import com.toan_itc.data.network.RestApi;

import java.io.File;
import java.util.concurrent.TimeUnit;

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
import rx.schedulers.Schedulers;

/**
 * Created by Toan.IT
 * Date: 04/07/2016
 */
@Module
public class NetworkModule {
    private File mCacheFile;
    private boolean isConnected;
    public NetworkModule(@NonNull File cacheFile,boolean checkConnect) {
        this.mCacheFile = cacheFile;
        this.isConnected=checkConnect;
    }

    @Singleton
    @Provides
    RestApi sRestClient(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }

    @Singleton
    @Provides
    Retrofit sRetrofit(@NonNull OkHttpClient okHttpClient,@NonNull RxJavaCallAdapterFactory rxJavaCallAdapter) {
        return new Retrofit.Builder()
                .baseUrl(RestApi.BaoOnline)
                .client(okHttpClient)
               // .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJavaCallAdapter)
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient makeOkHttpClient(@NonNull HttpLoggingInterceptor httpLoggingInterceptor,@NonNull Interceptor interceptor) {
        Cache cache = null;
        try {
            cache = new Cache(mCacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .cache(cache)
                .addNetworkInterceptor(interceptor)
               // .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }
   /* @Singleton
    @NonNull
    @Provides
    Gson providesGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.create();
    }

     private Gson provideGson() {
        final GsonBuilder builder = new GsonBuilder()
                .setExclusionStrategies(new RealmExclusionStrategy())
                .registerTypeAdapter(new TypeToken<RealmList<RealmString>>() {
                }.getType(), new RealmStringListAdapter());
        return builder.create();
    }*/
    @Singleton
    @Provides
    RxJavaCallAdapterFactory providesRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor makeLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }

    @Singleton
    @Provides
    Interceptor cache(){
        return chain -> {
            Request.Builder r = chain.request().newBuilder();
            if (isConnected) {
                int maxAge = 60;
                r.addHeader("Cache-Control", "public, max-age=" + maxAge);
            } else {
                int maxStale = 10 * 24 * 60 * 60; // 10 days
                r.addHeader("cache-control", "public, only-if-cached, max-stale=" + maxStale);
            }
            return chain.proceed(r.build());
        };
    }
}
