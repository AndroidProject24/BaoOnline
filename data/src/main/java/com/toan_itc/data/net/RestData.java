package com.toan_itc.data.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by toan.it
 * Date: 25/05/2016
 */
@Singleton
public class RestData {
    private final RestApi mRestApi;
    @Inject
    RestData(RestApi restApi) {
        mRestApi = restApi;
    }
    public Observable<String> GetRss(String url) {
        return mRestApi.GetRss(url)
                .map(this::parse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    private String parse(ResponseBody responseBody){
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(responseBody.byteStream()));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}
