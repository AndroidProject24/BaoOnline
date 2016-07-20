package com.toan_itc.baoonline.library.injector.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.toan_itc.data.local.PreferencesHelper;
import com.toan_itc.data.local.realm.RealmManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Toan.IT
 * Date: 04/07/2016
 */
@Module
public class RealmModule {
    private final Context context;

    public RealmModule(Context context) {
        this.context = context;
    }

    @Provides
    @NonNull
    PreferencesHelper mPreferencesHelper() {
        return new PreferencesHelper(context);
    }

    @Provides
    @NonNull
    @Singleton
    RealmManager mRealmManager() {
        return new RealmManager(context);
    }

/*
    //Repository

    @Provides
    @Singleton
    NamesRepository provideUserRepository(DataRepository userRepository) {
        return userRepository;
    }

    //Data source

    @Provides
    @Singleton
    DataSourceDisk provideNamesDataSourceDisk(RssDiskRealmMapper namesDiskRealmMapper){
        return new DataSourceDiskRealm(namesDiskRealmMapper);
    }

    // Mappers

    @Provides
    @Singleton
    RssDiskRealmMapper provideNamesDiskMapper(){
        return new RssDiskRealmMapper();
    }*/
}
