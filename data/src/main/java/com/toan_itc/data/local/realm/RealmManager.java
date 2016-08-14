package com.toan_itc.data.local.realm;

import android.content.Context;

import com.toan_itc.data.local.realm.cache.CacheObject;
import com.toan_itc.data.local.realm.cache.CacheService;
import com.toan_itc.data.local.realm.help.Migration;
import com.toan_itc.data.model.news.Data;
import com.toan_itc.data.model.news.ListNews;
import com.toan_itc.data.model.news.News;
import com.toan_itc.data.utils.Constants;
import com.toan_itc.data.utils.logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import rx.Observable;

/**
 * Created by Toan.IT
 * Date: 14/07/2016
 */
public class RealmManager implements CacheService {
    private final Context context;
    private Data news;
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

    @Override
    public <T, E extends RealmObject & CacheObject<T>> void set(final Class<E> clazz, T model) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.createObject(clazz).map(model));
        realm.close();
    }

    @Override
    public <T, E extends RealmObject & CacheObject<T>> Observable<T> get(final Class<E> clazz) {
        return Observable.create(subscriber -> {
            Realm realm = Realm.getDefaultInstance();
            for (E result : realm.where(clazz).findAll()) {
                subscriber.onNext(result.map());
            }
            subscriber.onCompleted();
            realm.close();
        });
    }

    @Override
    public <T, E extends RealmObject & CacheObject<T>> long count(Class<E> clazz) {
        Realm realm = Realm.getDefaultInstance();
        long count = realm.where(clazz).count();
        realm.close();
        return count;
    }

    @Override
    public <T, E extends RealmObject & CacheObject<T>> void deleteAll(Class<E> clazz) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.delete(clazz));
        realm.close();
    }

    public <T extends RealmObject> T add(T model) {
        Realm realm = getRealmInstance();
        realm.beginTransaction();
        realm.copyToRealm(model);
        realm.commitTransaction();
        return model;
    }

    public <T extends RealmObject> List<T> findAll(Class<T> clazz) {
        return getRealmInstance().where(clazz).findAll();
    }

    private Realm getRealmInstance() {
        return Realm.getDefaultInstance();
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
                realm.createOrUpdateAllFromJson(ListNews.class,stream);
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
    public Data getNews(int IdNews){
        getRealmInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                news = realm.where(ListNews.class).findAll().get(IdNews).getNews().get(IdNews).getData().first();
            }
        });
        return news;
    };
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
