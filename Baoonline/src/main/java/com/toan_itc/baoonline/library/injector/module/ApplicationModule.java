package com.toan_itc.baoonline.library.injector.module;

import android.app.Application;
import android.support.annotation.NonNull;

import com.toan_itc.baoonline.library.libs.image.FrescoImageLoader;
import com.toan_itc.baoonline.library.libs.image.ImageLoaderListener;
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
  @NonNull
  @Singleton
  Application provideApplication() {
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
  ImageLoaderListener provideImageLoader() {
    return new FrescoImageLoader();
  }

}