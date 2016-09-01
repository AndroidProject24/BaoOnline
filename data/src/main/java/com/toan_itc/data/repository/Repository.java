package com.toan_itc.data.repository;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.tickaroo.tikxml.TikXml;
import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.local.realm.RealmManager;
import com.toan_itc.data.model.newdetails.NewsDetails;
import com.toan_itc.data.model.rss.RssChannel;
import com.toan_itc.data.model.rss.RssFeed;
import com.toan_itc.data.network.RestApi;
import com.toan_itc.data.utils.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import okio.Buffer;
import rx.Observable;
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
        return mRestApi.getRss(url)
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
                /*.doOnNext(new Action1<RssChannel>() {
                    @Override
                    public void call(RssChannel rssChannel) {
                        //mRealmManager.set(RealmChannel.class,rssChannel);
                        Logger.wtf("GetRss","doOnNext");
                    }
                })*/
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
    ///Load News Details
    @RxLogObservable
    public Observable<NewsDetails> loadNews(String url) {
        return mRestApi.loadNews(url)
                .map(responseBody -> {
                    NewsDetails newsDetails=new NewsDetails();
                    loadData(url,responseBody,newsDetails);
                    return newsDetails;
                })
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler());
    }
    private void loadData(String url,ResponseBody responseBody,NewsDetails newsDetails){
        Document html = Jsoup.parse(parse(responseBody));
        if(url.startsWith("http://vietnamnet.vn/")) {
            Elements detail = html.select("div[class=ArticleContent]");
            for (Element details : detail) {
                details.select("img[class=logo-small]").remove();
                details.select("a").remove();
                newsDetails.setDetails(Constants.FIT_IMAGE + details.html());
            }
        }else if(url.startsWith("http://www.24h.com.vn/")){
            Elements detail=html.select("div[class= text-conent]");
            for (Element details:detail) {
                details.select("div[class=fb-like fb_iframe_widget]").remove();
                newsDetails.setDetails(Constants.FIT_IMAGE + details.html());
            }
        }else if(url.startsWith("http://www.nguoiduatin.vn/")){
            Elements detail=html.select("div[id=main-detail]");
            for (Element details:detail) {
                newsDetails.setDetails(Constants.FIT_IMAGE + details.html());
            }
        }else if(url.startsWith("http://cand.com.vn/")){
            Elements detail=html.select("div[id=links]");
            for (Element details:detail) {
                newsDetails.setDetails(Constants.FIT_IMAGE + details.html());
            }
        }else if(url.startsWith("http://kenh14.vn/")){
            Elements detail=html.select("div[class=content]");
            for(Element details:detail) {
                newsDetails.setDetails(Constants.FIT_IMAGE + details.html());
            }
        }else if(url.startsWith("http://ngoisao.net/")){
            Elements detail=html.select("div[class=fck_detail]");
            for(Element details:detail) {
                newsDetails.setDetails(Constants.FIT_IMAGE + details.html());
            }
        }else if(url.startsWith("http://www.doisongphapluat.com/")){
            Elements detail=html.select("div[id=main-detail]");
            detail.select("iframe").remove();
            for(Element details:detail) {
                newsDetails.setDetails(Constants.FIT_IMAGE + details.html());
            }
        }else if(url.startsWith("http://dantri.com.vn/")){
            Elements detail=html.select("div[class=fon34 mt3 mr2 fon43 detail-content]");
            detail.select("div[class=news-tag-list]").remove();
            for(Element details:detail) {
                newsDetails.setDetails(Constants.FIT_IMAGE + details.html());
            }
        }else if(url.startsWith("http://vnexpress.net/")){
            Elements detail=html.select("div[class=fck_detail width_common]");
            for(Element details:detail) {
                newsDetails.setDetails(Constants.FIT_IMAGE + details.html());
            }
        }
    }
}
