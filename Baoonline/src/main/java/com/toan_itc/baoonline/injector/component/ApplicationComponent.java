package com.toan_itc.baoonline.injector.component;

import com.toan_itc.baoonline.BaoOnlineApplication;
import com.toan_itc.baoonline.data.local.DatabaseRealm;
import com.toan_itc.baoonline.data.local.PreferencesHelper;
import com.toan_itc.baoonline.data.rxjava.RxBus;
import com.toan_itc.baoonline.data.service.RestClient;
import com.toan_itc.baoonline.injector.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {
    void inject(BaoOnlineApplication baoOnlineApplication);
    BaoOnlineApplication baoOnlineApplication();
/*
  void inject(MainPresenter mainPresenter);
  void inject(DetailPresenter detailPresenter);

  void inject(BaseApplication baseApplication);
  void inject(UnauthorisedInterceptor unauthorisedInterceptor);
*/
    RestClient mRestClient();
    RxBus mRxBus();
    PreferencesHelper mPreferencesHelper();
    DatabaseRealm mDatabaseRealm();
}