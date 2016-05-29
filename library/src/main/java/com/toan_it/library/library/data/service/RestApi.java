package com.toan_it.library.library.data.service;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */

public interface RestApi {
    String BaoOnline = "http://vnexpress.net/rss/";
    @GET()
    Observable<ResponseBody> GetRss(@Url String url);
}
