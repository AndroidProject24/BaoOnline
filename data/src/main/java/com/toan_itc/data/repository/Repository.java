package com.toan_itc.data.repository;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.tickaroo.tikxml.TikXml;
import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.local.realm.RealmManager;
import com.toan_itc.data.model.rss.RssChannel;
import com.toan_itc.data.model.rss.RssFeed;
import com.toan_itc.data.network.RestApi;
import com.toan_itc.data.utils.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import okio.Buffer;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Toan.IT
 * Date: 14/07/2016
 */
@Singleton
public class Repository {
    private final RestApi mRestApi;
    private final ThreadExecutor mThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;
    private final RealmManager mRealmManager;
    @Inject
    Repository(RestApi restApi, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,RealmManager realmManager) {
        this.mRestApi = restApi;
        this.mThreadExecutor=threadExecutor;
        this.mPostExecutionThread=postExecutionThread;
        this.mRealmManager=realmManager;
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
                .doOnNext(new Action1<RssChannel>() {
                    @Override
                    public void call(RssChannel rssChannel) {
                        //mRealmManager.set(RealmChannel.class,rssChannel);
                        Logger.wtf("GetRss","doOnNext");
                    }
                })
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler());
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
