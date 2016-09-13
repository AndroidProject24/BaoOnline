package com.toan_itc.data.repository;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.tickaroo.tikxml.TikXml;
import com.toan_itc.data.config.Constants;
import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.libs.reactivenetwork.ReactiveNetwork;
import com.toan_itc.data.local.realm.RealmManager;
import com.toan_itc.data.local.realm.entities.RssDiskRealmMapper;
import com.toan_itc.data.model.newdetails.NewsDetails;
import com.toan_itc.data.model.rss.RssChannel;
import com.toan_itc.data.model.rss.RssFeed;
import com.toan_itc.data.model.rssrealm.RealmFeedItem;
import com.toan_itc.data.network.RestApi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import okio.Buffer;
import rx.Observable;
import rx.schedulers.Schedulers;

import static com.toan_itc.data.config.StatusCodes.statusCodes;
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
    private final ReactiveNetwork mReactiveNetwork;
    @Inject
    Repository(RestApi restApi, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,ReactiveNetwork mReactiveNetwork,RealmManager realmManager) {
        this.mRestApi = restApi;
        this.mThreadExecutor=threadExecutor;
        this.mPostExecutionThread=postExecutionThread;
        this.mReactiveNetwork=mReactiveNetwork;
        this.mRealmManager=realmManager;
    }
	@RxLogObservable
	public Observable<statusCodes> getRss(String url,boolean isForced) {
		return Observable.concat(
				getRssFromRealm(isForced),
				getRssfromRetrofit(url),
				getDefaultResponse())
				.takeFirst(getListResponse -> getListResponse != statusCodes.LIST_NOT_AVAILABLE);
	}
	@RxLogObservable
	private Observable<statusCodes> getRssfromRetrofit(String url){
		return mReactiveNetwork.observeInternetConnectivity()
				.filter(connectionStatus -> connectionStatus)
				.switchMap(connectionStatus -> mRestApi.getRss(url))
				.map(responseBody -> {
					RssChannel rssChannel=null;
					try {
						TikXml parse = new TikXml.Builder().exceptionOnUnreadXml(false).build();
						rssChannel = parse.read(new Buffer().writeUtf8(parse(responseBody)), RssFeed.class).getChannel();
						RssDiskRealmMapper mapper=new RssDiskRealmMapper();
						mRealmManager.storeOrUpdateRssChannel(mapper.dataToModel(rssChannel));
					}catch (Exception e){
						e.printStackTrace();
					}
					return rssChannel.getItem().size() > 0 ?
							statusCodes.LIST_AVAILABLE :
							statusCodes.LIST_NOT_AVAILABLE;
				})
				.subscribeOn(Schedulers.from(mThreadExecutor))
				.observeOn(mPostExecutionThread.getScheduler());
	}
	@RxLogObservable
	private Observable<statusCodes> getRssFromRealm(boolean isForced) {
		return Observable.just(isForced)
				.filter(isForcedIn -> !isForcedIn)
				.map(isForcedIn -> (mRealmManager.getRssChannelListStatus()) ?
						statusCodes.LIST_AVAILABLE :
						statusCodes.LIST_NOT_AVAILABLE);
	}
	@RxLogObservable
	private Observable<statusCodes> getDefaultResponse() {
		return Observable.just(statusCodes.DEFAULT_RESPONSE);
	}
    @RxLogObservable
    public Observable<RssChannel> GetRss(String url) {
        return mRestApi.getRss(url)
		        .subscribeOn(Schedulers.from(mThreadExecutor))
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
	@RxLogObservable
	public Observable<List<RealmFeedItem>> getChannel(){
		return null;/*mRealmManager.getRealmInstance().where(RealmFeedItem.class).findAllAsync().asObservable()
				.filter(RealmResults::isLoaded)
				.filter(RealmResults::isValid)
				.filter(realmResults -> realmResults.size() > 0)
				*//*.map(new Func1<RealmResults<RealmFeedItem>, List<RealmFeedItem>>() {
					@Override
					public List<RealmFeedItem> call(RealmResults<RealmFeedItem> realmFeedItems) {
						if(realmFeedItems.size()>0)
						return mRealmManager.getRealmInstance().copyFromRealm(realmFeedItems);
						else
							return getRss("",true);
					}
				});*//*
				.flatMap(new Func1<RealmResults<RealmFeedItem>, Observable<statusCodes>>() {
					@Override
					public Observable<statusCodes> call(RealmResults<RealmFeedItem> realmFeedItems) {
						if(realmFeedItems.size()>0)
							return getRssFromRealm(true);
						else
							return getRssfromRetrofit("");
					}
				});*/
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
