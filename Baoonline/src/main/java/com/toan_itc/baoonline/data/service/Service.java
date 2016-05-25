package com.toan_itc.baoonline.data.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by toan.it
 * Date: 25/05/2016
 */
@Singleton
public class Service {
    private final RestClient sRestClient;
    @Inject
    public Service(RestClient restClient) {
        sRestClient = restClient;
    }
    public Observable<ResponseBody> GetRss(String url) {
        return sRestClient.GetRss(url)
                .subscribeOn(Schedulers.io());
              //  .observeOn(AndroidSchedulers.mainThread());
    }
}
