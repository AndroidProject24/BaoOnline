package com.toan_itc.data.network;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;


/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */

public interface RestApi {
    String BaoOnline = "https://medium.com/";
    @GET()
    Observable<ResponseBody> getRss(@Url String url);
    @GET()
    Observable<ResponseBody> loadNews(@Url String url);
}
