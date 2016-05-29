package com.toan_it.library.library.injector.module;

import android.app.Application;
import android.content.Context;

import com.toan_it.library.library.data.local.DatabaseRealm;
import com.toan_it.library.library.data.local.PreferencesHelper;
import com.toan_it.library.library.data.rxjava.RxBus;
import com.toan_it.library.library.data.service.RestApi;
import com.toan_it.library.library.data.service.RestClient;
import com.toan_it.library.library.injector.ApplicationContext;

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
  @ApplicationContext
  Context applicationContext() {
    return application;
  }

  @Provides
  @Singleton
  RestApi mRestApi() {
    return RestClient.sRestClient();
  }

  @Provides
  @Singleton
  RxBus mRxBus() {
    return new RxBus();
  }

  @Provides
  @Singleton
  PreferencesHelper mPreferencesHelper() {
    return new PreferencesHelper(application);
  }

  @Provides
  @Singleton
  DatabaseRealm mDatabaseRealm() {
    return new DatabaseRealm(application);
  }
}