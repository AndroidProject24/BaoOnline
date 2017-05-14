package com.toan_itc.data.repository.remote;

import android.support.annotation.NonNull;
import bolts.Task;
import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.tickaroo.tikxml.TikXml;
import com.toan_itc.data.config.Constants;
import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.libs.reactivenetwork.ReactiveNetwork;
import com.toan_itc.data.local.realm.entities.RssChannelMapper;
import com.toan_itc.data.model.newdetails.NewsDetails;
import com.toan_itc.data.model.rss.RssChannel;
import com.toan_itc.data.model.rss.RssFeed;
import com.toan_itc.data.model.rssrealm.RealmChannel;
import com.toan_itc.data.model.rssrealm.RealmFeedItem;
import com.toan_itc.data.network.RestApi;
import com.toan_itc.data.repository.DataSource;
import com.toan_itc.data.repository.disk.DiskDataSource;
import com.toan_itc.data.utils.logger.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Toan.IT
 * Date: 13/09/2016
 * Email: huynhvantoan.itc@gmail.com
 */

public class CloudDataSource implements DataSource {
  private final RestApi mRestApi;
  private final ThreadExecutor mThreadExecutor;
  private final PostExecutionThread mPostExecutionThread;
  private final ReactiveNetwork mReactiveNetwork;
  private final DiskDataSource mDiskDataSource;
  public CloudDataSource( @NonNull DiskDataSource diskDataSource,@NonNull RestApi restApi, @NonNull ReactiveNetwork reactiveNetwork, @NonNull ThreadExecutor threadExecutor, @NonNull PostExecutionThread postExecutionThread) {
    this.mDiskDataSource=diskDataSource;
    this.mRestApi = restApi;
    this.mReactiveNetwork=reactiveNetwork;
    this.mThreadExecutor=threadExecutor;
    this.mPostExecutionThread=postExecutionThread;
  }
  @RxLogObservable
  @Override
  public Observable<List<RealmFeedItem>> getListHome(@NonNull String url) {
    return mRestApi.getRss(url)
        .doOnNext(responseBody -> {
          RealmChannel realmChannel=null;
          try {
            TikXml parse = new TikXml.Builder().exceptionOnUnreadXml(false).build();
            RssChannel
                rssChannel = parse.read(new Buffer().writeUtf8(parse(responseBody)), RssFeed.class).getChannel();
            RssChannelMapper rssChannelMapper=new RssChannelMapper();
            realmChannel=rssChannelMapper.dataToModel(rssChannel);
            Logger.e("CloudDataSource:getListHome"+url);
            mDiskDataSource.saveListHome(realmChannel);
          }catch (Exception e){
            e.printStackTrace();
          }
        })
        .flatMap(
            (Func1<ResponseBody, Observable<List<RealmFeedItem>>>) responseBody -> Observable.just(null))
        .doOnError(Throwable::printStackTrace);
    /*return mReactiveNetwork.observeInternetConnectivity()
            .filter(connectionStatus -> connectionStatus)
            .switchMap(connectionStatus -> mRestApi.getRss(url))
            .flatMap(new Func1<ResponseBody, Observable<List<RssFeedItem>>>() {
              @Override
              public Observable<List<RssFeedItem>> call(ResponseBody responseBody) {
                RssChannel rssChannel=null;
                try {
                  TikXml parse = new TikXml.Builder().exceptionOnUnreadXml(false).build();
                  rssChannel = parse.read(new Buffer().writeUtf8(parse(responseBody)), RssFeed.class).getChannel();
                }catch (Exception e){
                  e.printStackTrace();
                }
                return Observable.from(rssChannel.getItem()).doOnNext(new Action1<RssFeedItem>() {
                  @Override
                  public void call(RssFeedItem rssFeedItem) {
                    mDiskDataSource.retainList(rssFeedItem);
                    mCachedTasks.put(rssFeedItem.getTitle(), rssFeedItem);
                  }
                }).toList();
              }
            })
            .map(new Func1<List<RssFeedItem>, statusCodes>() {
              @Override
              public statusCodes call(List<RssFeedItem> list) {
                return list.size() > 0 ?
                        statusCodes.LIST_AVAILABLE :
                        statusCodes.LIST_NOT_AVAILABLE;
              }
            })
            .doOnCompleted(new Action0() {
              @Override
              public void call() {
                mCacheIsDirty = false;
              }
            })
            .subscribeOn(Schedulers.from(mThreadExecutor))
            .observeOn(mPostExecutionThread.getScheduler());*/
  }

  @Override
  public Observable<RealmFeedItem> getObject(@NonNull String FeedId) {
    return null;
  }

  @Override
  public void saveListHome(@NonNull RealmChannel realmChannel) {

  }

  @Override
  public void completeTask(@NonNull Task task) {

  }

  @Override
  public void completeTask(@NonNull String taskId) {

  }

  @Override
  public void activateTask(@NonNull Task task) {

  }

  @Override
  public void activateTask(@NonNull String taskId) {

  }

  @Override
  public void clearCompletedTasks() {

  }

  @Override
  public void refreshTasks() {

  }

  @Override
  public void deleteAllTasks() {

  }

  @Override
  public void deleteTask(@NonNull String taskId) {

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
