package com.toan_itc.baoonline.injector.module;

import android.content.Context;

import com.toan_itc.baoonline.BaoOnlineApplication;
import com.toan_itc.baoonline.data.local.DatabaseRealm;
import com.toan_itc.baoonline.data.local.PreferencesHelper;
import com.toan_itc.baoonline.data.rxjava.RxBus;
import com.toan_itc.baoonline.data.service.RestClient;
import com.toan_itc.baoonline.injector.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

  private final BaoOnlineApplication baseApplicaton;

  public ApplicationModule(BaoOnlineApplication baseApplication) {
    this.baseApplicaton = baseApplication;
  }
  @Provides
  @Singleton
  BaoOnlineApplication application() {
    return baseApplicaton;
  }

  @Provides
  @ApplicationContext
  public Context applicationContext() {
    return baseApplicaton;
  }

  @Provides
  @Singleton
  public RestClient mRestClient() {
    return RestClient.Creator.sRestClient(baseApplicaton);
  }

  @Provides
  @Singleton
  public RxBus mRxBus() {
    return new RxBus();
  }

  @Provides
  @Singleton
  public PreferencesHelper mPreferencesHelper() {
    return new PreferencesHelper(baseApplicaton);
  }

  @Provides
  @Singleton
  public DatabaseRealm mDatabaseRealm() {
    return new DatabaseRealm(baseApplicaton);
  }
}