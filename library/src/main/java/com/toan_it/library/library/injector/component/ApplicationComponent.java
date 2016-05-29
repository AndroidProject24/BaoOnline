package com.toan_it.library.library.injector.component;

import android.app.Application;
import android.content.Context;

import com.toan_it.library.library.data.local.DatabaseRealm;
import com.toan_it.library.library.data.local.PreferencesHelper;
import com.toan_it.library.library.data.rxjava.RxBus;
import com.toan_it.library.library.data.service.RestApi;
import com.toan_it.library.library.injector.ApplicationContext;
import com.toan_it.library.library.injector.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {
    @ApplicationContext
    Context context();
    Application application();
    RestApi mRestApi();
    RxBus mRxBus();
    PreferencesHelper mPreferencesHelper();
    DatabaseRealm mDatabaseRealm();
}