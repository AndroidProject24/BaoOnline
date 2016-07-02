package com.toan_it.library.library.injector.component;

import android.support.annotation.NonNull;

import com.toan_it.library.library.data.local.DatabaseRealm;
import com.toan_it.library.library.data.local.PreferencesHelper;
import com.toan_it.library.library.data.networking.RestApi;
import com.toan_it.library.library.data.networking.RestData;
import com.toan_it.library.library.data.rxjava.RxBus;
import com.toan_it.library.library.injector.module.ApplicationModule;
import com.toan_it.library.library.injector.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    @NonNull
    RestData mRestData();

    @NonNull
    RxBus mRxBus();

    @NonNull
    PreferencesHelper mPreferencesHelper();

    @NonNull
    DatabaseRealm mDatabaseRealm();

    @NonNull
    RestApi mRestApi();
}