package com.toan_itc.data.repository;

import android.support.annotation.NonNull;
import bolts.Task;
import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.libs.reactivenetwork.ReactiveNetwork;
import com.toan_itc.data.local.realm.RealmManager;
import com.toan_itc.data.model.rss.RssChannel;
import com.toan_itc.data.model.rssrealm.RealmChannel;
import com.toan_itc.data.model.rssrealm.RealmFeedItem;
import com.toan_itc.data.network.RestApi;
import com.toan_itc.data.repository.disk.DiskDataSource;
import com.toan_itc.data.repository.remote.CloudDataSource;
import com.toan_itc.data.utils.logger.Logger;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.functions.Func1;

import static com.toan_itc.data.config.StatusCodes.statusCodes;

/**
 * Created by Toan.IT
 * Date: 14/07/2016
 */
@Singleton
public class Repository implements DataSource{
    private final RestApi mRestApi;
    private final ThreadExecutor mThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;
    private final RealmManager mRealmManager;
    private final ReactiveNetwork mReactiveNetwork;
	private final DiskDataSource mDiskDataSource;
    private final CloudDataSource mCloudDataSource;
    @Inject
    Repository(@NonNull RestApi restApi, @NonNull ThreadExecutor threadExecutor, @NonNull PostExecutionThread postExecutionThread, @NonNull ReactiveNetwork reactiveNetwork, @NonNull RealmManager realmManager, @NonNull DiskDataSource diskDataSource,CloudDataSource mCloudDataSource) {
      this.mRestApi = restApi;
      this.mThreadExecutor=threadExecutor;
      this.mPostExecutionThread=postExecutionThread;
      this.mReactiveNetwork=reactiveNetwork;
      this.mRealmManager=realmManager;
      this.mDiskDataSource=diskDataSource;
      this.mCloudDataSource=mCloudDataSource;
    }
	@RxLogObservable
	public Observable<statusCodes> getRss(String url,boolean isForced) {
		return Observable.concat(
				getRssFromRealm(isForced),
				getRssfromRetrofit(url),
				getDefaultResponse());
	//			.takeFirst(getListResponse -> getListResponse != statusCodes.LIST_NOT_AVAILABLE);
	}

	@RxLogObservable
	private Observable<statusCodes> getRssfromRetrofit(String url){
		return null;/*mReactiveNetwork.observeInternetConnectivity()
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
	@RxLogObservable
	private Observable<statusCodes> getRssFromRealm(boolean isForced) {
		return null;/*Observable.just(isForced)
				.filter(isForcedIn -> !isForcedIn)
				.map(isForcedIn -> (mRealmManager.getRssChannelListStatus()) ?
						statusCodes.LIST_AVAILABLE :
						statusCodes.LIST_NOT_AVAILABLE);*/
	}
	@RxLogObservable
	private Observable<statusCodes> getDefaultResponse() {
      return null;
		/*return Observable.just(statusCodes.DEFAULT_RESPONSE)
				.map(new Func1<statusCodes, statusCodes>() {
					@Override
					public statusCodes call(statusCodes statusCodes) {
						return null;
					}
				});*/
	}
    @RxLogObservable
    public Observable<RssChannel> GetRss(String url) {
        return null;/* mRestApi.getRss(url)
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
                .observeOn(mPostExecutionThread.getScheduler());*/
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

  /////////////////////////////////////////////////////////////////
  @RxLogObservable
  @Override
  public Observable<List<RealmFeedItem>> getListHome(@NonNull String url) {
    Observable<List<RealmFeedItem>> remote = mDiskDataSource.getListHome(url);
    Observable<List<RealmFeedItem>> local = mCloudDataSource.getListHome(url);
    return Observable.concat(mDiskDataSource.getListHome(url),mCloudDataSource.getListHome(url))
        .map(new Func1<List<RealmFeedItem>, List<RealmFeedItem>>() {
          @Override public List<RealmFeedItem> call(List<RealmFeedItem> list) {
            Logger.e("getListHome="+list.toString());
            return list;
          }
        })
        .filter(data -> !data.isEmpty())
        .first();
  }

  @Override
  public Observable<RealmFeedItem> getObject(@NonNull String FeedId) {
    return null;
  }

  @Override
  public void saveListHome(@NonNull RealmChannel realmFeedItem) {

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
}
