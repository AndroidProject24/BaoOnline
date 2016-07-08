package com.toan_itc.baoonline.library.data.local;

import android.content.Context;

import com.toan_itc.baoonline.library.mvp.model.news.ListNews;
import com.toan_itc.baoonline.library.utils.Constant;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;

@Singleton
public class DatabaseRealm {
    private Context mContext;
    @Inject
    public DatabaseRealm(Context Context){
        mContext=Context;
    }
    public void setup() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(mContext)
                .name(Constant.BaoOnline_Realm)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
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
