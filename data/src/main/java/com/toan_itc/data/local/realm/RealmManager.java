package com.toan_itc.data.local.realm;

import android.content.Context;

import com.toan_itc.data.config.Constants;
import com.toan_itc.data.local.realm.entities.RssDiskRealm;
import com.toan_itc.data.local.realm.help.Migration;
import com.toan_itc.data.model.bookmark.NewsBookmark;
import com.toan_itc.data.model.news.Data;
import com.toan_itc.data.model.news.News;
import com.toan_itc.data.model.rssrealm.RealmChannel;
import com.toan_itc.data.utils.logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

/**
 * Created by Toan.IT
 * Date: 14/07/2016
 */
public class RealmManager extends AbstractRealm{
    private final Context context;
    public RealmManager(Context context){
        this.context = context;
    }

    public void initialize(){
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(context)
                .name(Constants.BaoOnline_Realm)
                .schemaVersion(Constants.RealmVersion)
                .deleteRealmIfMigrationNeeded()
                .migration(new Migration())
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public Realm getRealmInstance() {
        return Realm.getDefaultInstance();
    }

	public <T extends RealmObject> T add(T model) {
        Realm realm = getRealmInstance();
        realm.beginTransaction();
        realm.copyToRealm(model);
        realm.commitTransaction();
        return model;
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

    public void closeRealm(){
        Realm realm=getRealmInstance();
        if(!realm.isClosed())
            realm.close();
    }

    public void Set_News(Context context){
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
