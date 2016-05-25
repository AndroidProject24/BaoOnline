package com.toan_itc.baoonline.injector.component;

import android.content.Context;

import com.toan_itc.baoonline.BaoOnlineApplication;
import com.toan_itc.baoonline.data.local.DatabaseRealm;
import com.toan_itc.baoonline.data.local.PreferencesHelper;
import com.toan_itc.baoonline.data.rxjava.RxBus;
import com.toan_itc.baoonline.data.service.RestClient;
import com.toan_itc.baoonline.injector.ApplicationContext;
import com.toan_itc.baoonline.injector.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {
    void inject(BaoOnlineApplication baoOnlineApplication);
    @ApplicationContext
    Context context();
    BaoOnlineApplication baoOnlineApplication();
    RestClient mRestClient();
    RxBus mRxBus();
    PreferencesHelper mPreferencesHelper();
    DatabaseRealm mDatabaseRealm();
}