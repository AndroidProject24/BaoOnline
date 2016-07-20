package com.toan_itc.data.network;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.tickaroo.tikxml.TikXml;
import com.toan_itc.data.model.rss.RssChannel;
import com.toan_itc.data.model.rss.RssFeed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import okio.Buffer;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/*
*
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
    @RxLogObservable
    public Observable<RssChannel> GetRss(String url) {
        return mRestApi.GetRss(url)
                .map(responseBody -> {
                    RssChannel rssChannel=null;
                    try {
                        TikXml parse = new TikXml.Builder().exceptionOnUnreadXml(false).build();
                        rssChannel = parse.read(new Buffer().writeUtf8(parse(responseBody)), RssFeed.class).getChannel();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return rssChannel;
                })
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
