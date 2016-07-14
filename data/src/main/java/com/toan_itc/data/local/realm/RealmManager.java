package com.toan_itc.data.local.realm;

import android.content.Context;

import com.toan_itc.data.local.realm.help.Migration;
import com.toan_itc.data.utils.Constants;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Toan.IT
 * Date: 14/07/2016
 */

public class RealmManager {
    private final Context context;
    @Inject
    public RealmManager(Context context){
        this.context = context;
    }

    public void initialize(){
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(context)
                .name(Constants.BaoOnline_Realm)
                .schemaVersion(Constants.RealmVersion)
                .migration(new Migration())
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
