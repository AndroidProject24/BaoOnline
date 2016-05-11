package com.toan_itc.baoonline.data.local;

import android.content.Context;

import com.toan_itc.baoonline.mvp.model.news.ListNews;
import com.toan_itc.baoonline.utils.Constants;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

@Singleton
public class DatabaseRealm {
    private Context mContext;
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
        Timber.e("setup");
    }
    public Realm getRealmInstance() {
        return Realm.getDefaultInstance();
    }
    public void closeRealm(){
        Realm realm=getRealmInstance();
        if(!realm.isClosed())
        realm.close();
    }
    public void Get_News(Context context){
        getRealmInstance().executeTransactionAsync(realm -> {
            InputStream stream =null;
            try {
                stream= context.getAssets().open("baoonline.json");
                realm.createOrUpdateObjectFromJson(ListNews.class,stream);
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
}
