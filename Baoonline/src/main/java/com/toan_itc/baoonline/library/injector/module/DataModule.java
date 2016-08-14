package com.toan_itc.baoonline.library.injector.module;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
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
public class DataModule {

    private Application application;

    public DataModule(Application application) {
        this.application = application;
    }

    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @NonNull
    PreferencesHelper mPreferencesHelper() {
        return new PreferencesHelper(application);
    }

    @Provides
    @NonNull
    @Singleton
    RealmManager mRealmManager() {
        return new RealmManager(application);
    }

}
