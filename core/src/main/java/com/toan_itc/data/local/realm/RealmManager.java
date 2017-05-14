package com.toan_itc.data.local.realm;

import android.content.Context;
import android.support.annotation.NonNull;

import com.toan_itc.data.local.realm.entities.RssDiskRealm;
import com.toan_itc.data.model.bookmark.NewsBookmark;
import com.toan_itc.data.model.news.Data;
import com.toan_itc.data.model.news.News;
import com.toan_itc.data.model.rssrealm.RealmChannel;
import com.toan_itc.data.model.rssrealm.RealmFeedItem;
import com.toan_itc.data.repository.DataSource;
import com.toan_itc.data.utils.logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import bolts.Task;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;

/**
 * Created by Toan.IT
 * Date: 14/07/2016
 */
public class RealmManager extends AbstractRealm implements DataSource{
  public RealmManager(){
    super();
  }

  private static Realm getRealmInstance() {
    return Realm.getDefaultInstance();
  }

  public <T extends RealmObject> T add(T model) {
    Realm realm = getRealmInstance();
    realm.beginTransaction();
    realm.copyToRealm(model);
    realm.commitTransaction();
    return model;
  }

  public <T extends RealmObject> T update(T model) {
    Realm realm = getRealmInstance();
    realm.beginTransaction();
    realm.copyToRealmOrUpdate(model);
    realm.commitTransaction();
    return model;
  }

  public <T extends RealmObject> void delete(Class<T> clazz, String field, String value) {
    Realm realm = getRealmInstance();
    RealmResults<T> models = realm.where(clazz).equalTo(field, value).findAll();
    realm.beginTransaction();
    models.deleteAllFromRealm();
    realm.commitTransaction();
  }

  public <T extends RealmObject> void delete(Class<T> clazz, String field, Integer value) {
    Realm realm = getRealmInstance();
    RealmResults<T> models = realm.where(clazz).equalTo(field, value).findAll();
    realm.beginTransaction();
    models.deleteAllFromRealm();
    realm.commitTransaction();
  }


  public <T extends RealmObject> List<T> findAll(Class<T> clazz) {
    return getRealmInstance().copyFromRealm(getRealmInstance().where(clazz).findAll());
  }

  public <T extends RealmObject> List<T> findAllAsync(Class<T> clazz) {
    return getRealmInstance().where(clazz).findAllAsync();
  }

  public <T extends RealmObject> T findFist(Class<T> clazz) {
    return getRealmInstance().where(clazz).findFirst();
  }

  public <T extends RealmObject> T findLast(Class<T> clazz) {
    return getRealmInstance().where(clazz).findAll().last();
  }

  public <T extends RealmObject> long size(Class<T> clazz) {
    return getRealmInstance().where(clazz).count();
  }
  private <T extends RealmObject> RealmQuery<T> query(Class<T> clazz) {
    return getRealmInstance().where(clazz);
  }

  public <T extends RealmObject> List<T> findSortedAscending(Class<T> clazz, String field) {
    RealmResults<T> results = query(clazz).findAll();
    results.sort(field, Sort.ASCENDING);
    return results;
  }

  public <T extends RealmObject> Number findMax(Class<T> clazz, String field) {
    return query(clazz).max(field);
  }

  public <T extends RealmObject> List<T> findWhere(Class<T> clazz, String field, String value) {
    return query(clazz).equalTo(field, value).findAll();
  }

  public <T extends RealmObject> List<T> findCopyWhere(Class<T> clazz, String field, Integer value, String orderBy) {
    RealmResults<T> results = query(clazz).equalTo(field, value).findAll();
    if (orderBy != null) {
      results.sort(orderBy, Sort.ASCENDING);
    }
    return getRealmInstance().copyFromRealm(results);
  }
  public void closeRealm(){
    Realm realm=getRealmInstance();
    if(!realm.isClosed())
      realm.close();
  }

  private static int getNextPrimaryKeyValue() {
    Realm realm = Realm.getDefaultInstance();
    Number curr_max = realm.where(RealmFeedItem.class).findAll().max("id");
    int new_max = 1;
    if (curr_max != null) {
      new_max = curr_max.intValue();
      new_max++;
    }
    return new_max;
  }


  public static void Set_News(Context context){
    getRealmInstance().executeTransaction(realm -> {
      InputStream stream =null;
      try {
        stream= context.getAssets().open("baoonline.json");
        realm.createOrUpdateAllFromJson(News.class,stream);
      }catch (Exception e){
        e.printStackTrace();
      }finally {
        if (stream != null) {
          try {
            stream.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    });
  }

  //Add data
  public void Set_Data(String title,String url){
    getRealmInstance().executeTransactionAsync(realm -> {
      int id=0;
      Data data=realm.createObject(Data.class);
      if(realm.where(Data.class).max("Id")==null){
        id=1;
      }else {
        AtomicLong productPrimaryKey = new AtomicLong(realm.where(Data.class).max("Id").longValue() + 1);
        id =(int) productPrimaryKey.getAndIncrement();
      }
      Logger.e(title+"url="+url);
      data.setId(id);
      data.setTitle(title);
      data.setUrl(url);
      realm.copyToRealmOrUpdate(data);
    });
  }

  public void Set_News(String titlenews){
    getRealmInstance().executeTransactionAsync(realm -> {
      int id=0;
      News news = realm.createObject(News.class);
      if(realm.where(Data.class).max("Id")==null) {
        id=1;
      }else {
        AtomicLong productPrimaryKey = new AtomicLong(realm.where(News.class).max("Id").longValue() + 1);
        id =(int) productPrimaryKey.getAndIncrement();
      }
      news.setId(id);
      news.setTitle(titlenews);
      realm.copyToRealmOrUpdate(news);
    });
  }

  public News getnews(String title){
    if(title!=null&&!title.equalsIgnoreCase(""))
      return getRealmInstance().where(News.class).equalTo("Title",title).findFirst();
    else
      return null;
  }

  @Override
  public void clearListDatabase() {
    Realm realm = getRealmInstance();
    realm.executeTransaction(realm1 -> realm1.delete(RssDiskRealm.class));
    realm.close();
  }

  @Override
  public void clearDetailsDatabase() {
    Realm realm = getRealmInstance();
    realm.executeTransaction(realm1 -> realm1.delete(RssDiskRealm.class));
    realm.close();
  }

  @Override
  public void clearBookmarkDatabase() {
    Realm realm = getRealmInstance();
    realm.executeTransaction(realm1 -> realm1.delete(NewsBookmark.class));
    realm.close();
  }

  //Bookmark

  public void storeOrUpdateNewsList(List<NewsBookmark> newsBookmarks) {
    Realm realm = getRealmInstance();
    realm.executeTransaction(realm1 -> realm1.insertOrUpdate(newsBookmarks));
    realm.close();
  }

  public void storeOrUpdateNews(NewsBookmark newsBookmarks) {
    Realm realm = getRealmInstance();
    realm.executeTransaction(realm1 -> realm1.insertOrUpdate(newsBookmarks));
    realm.close();
  }

  public Boolean getBookmarkListStatus() {
    Realm realm = getRealmInstance();
    boolean dataStatus;
    dataStatus = (realm.where(NewsBookmark.class).count() > 0);
    realm.close();
    return dataStatus;
  }

  //RssChannel
  public void storeOrUpdateRssChannel(RealmChannel rssChannel) {
    Realm realm = getRealmInstance();
    realm.executeTransaction(realm1 -> realm1.insertOrUpdate(rssChannel));
    realm.close();
  }

  public Boolean getRssChannelListStatus() {
    Realm realm = getRealmInstance();
    boolean dataStatus;
    dataStatus = (realm.where(RealmChannel.class).count() > 0);
    realm.close();
    return dataStatus;
  }

  @Override
  public Observable<List<RealmFeedItem>> getListHome(@NonNull String url) {
    return null;
  }

  @Override
  public Observable<RealmFeedItem> getObject(@NonNull String FeedId) {
    return null;
  }

  @Override
  public void saveListHome(@NonNull RealmChannel realmChannel) {
    getRealmInstance().executeTransaction(realm1 -> realm1.copyToRealm(realmChannel));
    closeRealm();
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

  //Page Size,Page Index
    /*public void getFromDatabase(int pageSize, int page) {
        if (page == 1) mEntityList.clear();
        RealmResults<RealmMoment> zcoolRealmResults = mRealm.where(RealmMoment.class).findAll();
        int startPos = Math.max(zcoolRealmResults.size() - 1 - (page - 1) * pageSize, 0);
        int endPos = Math.max(startPos - pageSize, 0);

        for (int i = startPos; i > endPos; i--) {
            mEntityList.add(zcoolRealmResults.get(i).toEntity());
        }
    }*/

}
