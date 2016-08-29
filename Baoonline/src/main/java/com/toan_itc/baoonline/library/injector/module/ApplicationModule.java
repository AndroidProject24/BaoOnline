package com.toan_itc.baoonline.library.injector.module;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.toan_itc.data.libs.image.FrescoImageLoader;
import com.toan_itc.data.libs.image.ImageLoaderListener;
import com.toan_itc.data.local.PreferencesHelper;
import com.toan_itc.data.local.realm.RealmManager;
import com.toan_itc.data.rxjava.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

  protected final Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    RxBus mRxBus() {
        return new RxBus();
    }

    @Provides
    @Singleton
    ImageLoaderListener provideImageLoader() {
        return new FrescoImageLoader();
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
    @Singleton
    RealmManager mRealmManager() {
        return new RealmManager(application);
    }

}