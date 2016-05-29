package com.toan_it.library.library.data.service;

import com.toan_it.library.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by toan.it
 * Date: 28/05/2016
 */
public class RestClient {
   /*
    class Creator {
        public static RestClient sRestClient(Context context) {
            File cacheFile= new File(context.getCacheDir(), Constant.HTTP_CACHE);
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.connectTimeout(30, TimeUnit.SECONDS);
            client.writeTimeout(30, TimeUnit.SECONDS);
            client.readTimeout(30, TimeUnit.SECONDS);
            client.cache(cache);
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(interceptor).build();
            OkHttpClient okHttpClient = client.build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaoOnline)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(RestClient.class);
        }
    }*/
    public static RestApi sRestClient() {
        OkHttpClient okHttpClient = makeOkHttpClient(makeLoggingInterceptor());
        return sRestClient(okHttpClient);
    }

    private static RestApi sRestClient(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestApi.BaoOnline)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(RestApi.class);
    }

    private static OkHttpClient makeOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
       // File cacheFile= new File(context.getCacheDir(), Constant.HTTP_CACHE);
       // Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
        return new OkHttpClient.Builder()
               // .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    private static HttpLoggingInterceptor makeLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }
    //Offline
   /* @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder r = chain.request().newBuilder();
        if (isConnected()) {
            int maxAge = 2 * 60;
            r.addHeader("cache-control", "public, max-age=" + maxAge);
        } else {
            int maxStale = 30 * 24 * 60 * 60; // 30 days
            r.addHeader("cache-control", "public, only-if-cached, max-stale=" + maxStale);
        }

        return chain.proceed(r.build());
    }

    protected boolean isConnected() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }*/
}