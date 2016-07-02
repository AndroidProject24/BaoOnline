package com.toan_it.library.library.injector.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.toan_it.library.library.data.local.DatabaseRealm;
import com.toan_it.library.library.data.local.PreferencesHelper;
import com.toan_it.library.library.data.rxjava.RxBus;
import com.toan_it.library.library.injector.qualifier.ApplicationContext;
import com.toan_it.library.library.libs.image.FrescoImageLoader;
import com.toan_it.library.library.libs.image.ImageLoaderListener;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

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
  @ApplicationContext
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
  @Singleton
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
  public Fresco provideFresco(@NonNull Application application, @NonNull OkHttpClient okHttpClient) {
    return new Picasso.Builder(application)
            .downloader(new OkHttp3Downloader(okHttpClient))
            .build();
  }

  @Provides
  @NonNull
  @Singleton
  public ImageLoaderListener provideImageLoader() {
    return new FrescoImageLoader();
  }
}