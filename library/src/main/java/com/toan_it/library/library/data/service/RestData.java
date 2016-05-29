package com.toan_it.library.library.data.service;

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
    public RestData(RestApi restApi) {
        mRestApi = restApi;
    }
    public Observable<ResponseBody> GetRss(String url) {
        return mRestApi.GetRss(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
