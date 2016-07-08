package com.toan_itc.baoonline.library.injector.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.toan_itc.baoonline.library.data.local.DatabaseRealm;
import com.toan_itc.baoonline.library.data.local.PreferencesHelper;
import com.toan_itc.baoonline.library.data.rxjava.RxBus;
import com.toan_itc.baoonline.library.libs.image.FrescoImageLoader;
import com.toan_itc.baoonline.library.libs.image.ImageLoaderListener;

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
  @NonNull
  @Singleton
  Application provideApplication() {
    return application;
  }

  @Provides
  Context applicationContext() {
    return application;
  }

  @Provides
  @NonNull
  @Singleton
  RxBus mRxBus() {
    return new RxBus();
  }

  @Provides
  @NonNull
  PreferencesHelper mPreferencesHelper() {
    return new PreferencesHelper(application);
  }

  @Provides
  @NonNull
  @Singleton
  DatabaseRealm mDatabaseRealm() {
    return new DatabaseRealm(application);
  }

  @Provides
  @NonNull
  @Singleton
  ImageLoaderListener provideImageLoader() {
    return new FrescoImageLoader();
  }
}