package com.toan_it.library.library.data.networking;

import com.toan_it.library.library.mvp.model.toan;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */

public interface RestApi {
    String BaoOnline = "https://raw.githubusercontent.com/artem-zinnatullin/qualitymatters/master/rest_api/";
    @GET()
    Observable<ResponseBody> GetRss(@Url String url);

    @GET("items")
    Observable<List<toan>> gettest();
}
