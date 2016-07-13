package com.toan_itc.data.local;

import android.content.Context;

import com.toan_itc.data.model.news.Data;
import com.toan_itc.data.model.news.ListNews;
import com.toan_itc.data.model.news.News;
import com.toan_itc.data.utils.Constants;
import com.toan_itc.data.utils.logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;

@Singleton
public class DatabaseRealm {
    private Context mContext;
    private Data news;
    @Inject
    public DatabaseRealm(Context Context){
        mContext=Context;
    }
    public void setup() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(mContext)
                .name(Constants.BaoOnline_Realm)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
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
