package com.toan_itc.data.repository.disk;

import android.support.annotation.NonNull;
import bolts.Task;
import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.toan_itc.data.local.realm.RealmManager;
import com.toan_itc.data.model.rssrealm.RealmChannel;
import com.toan_itc.data.model.rssrealm.RealmFeedItem;
import com.toan_itc.data.repository.DataSource;
import com.toan_itc.data.utils.logger.Logger;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

/**
 * Created by Toan.IT
 * Date: 13/09/2016
 * Email: huynhvantoan.itc@gmail.com
 */

public class DiskDataSource implements DataSource {
  private RealmManager mRealmManager;
  public DiskDataSource(RealmManager realmManager) {
    this.mRealmManager = realmManager;
  }

  @RxLogObservable
  @Override
  public Observable<List<RealmFeedItem>> getListHome(@NonNull String url) {
    /*return Observable.defer((Func0<Observable<List<RealmFeedItem>>>) () -> {
      List<RealmFeedItem> realmFeedItemList = new ArrayList<>();
      if (mRealmManager.size(RealmChannel.class) > 0) {
        realmFeedItemList = mRealmManager.findAll(RealmChannel.class).get(0).getItem();
      }
      Logger.e("DiskDataSource:getListHome" + realmFeedItemList.toString());
      return Observable.just(realmFeedItemList);
    });*/
    List<RealmFeedItem> realmFeedItemList=new ArrayList<>();
    if(mRealmManager.size(RealmChannel.class)>0) {
      realmFeedItemList = mRealmManager.findAll(RealmChannel.class).get(0).getItem();
    }
    Logger.e("DiskDataSource:getListHome"+realmFeedItemList.toString());
    return Observable.just(realmFeedItemList);
  }

  @Override
  public Observable<RealmFeedItem> getObject(@NonNull String FeedId) {
    return null;
  }

  @Override
  public void saveListHome(@NonNull RealmChannel realmFeedItem) {
    mRealmManager.saveListHome(realmFeedItem);
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
/*
	@Override
	public Observable<RealmFeedItem> getObject() {
		return null;
	}

	@Override
	public void saveList(@NonNull Task task) {

	}

	@Override
	public void completeList(@NonNull Task task) {

	}

	@Override
	public void clearCompleted() {

	}

	@Override
	public void refreshList() {

	}

	@Override
	public void deleteAllList() {

	}

	@Override
	public void deleteObject(@NonNull String taskId) {

	}

	@Override
	public void retainList(RssFeedItem feedItemList) {
		//RssChannelMapper mapper=new RssChannelMapper();
		//mRealmManager.storeOrUpdateRssChannel(mapper.dataToModel(rssChannel));
	}

	@Override
	public boolean isCached() {
		return false;
	}*/
}
