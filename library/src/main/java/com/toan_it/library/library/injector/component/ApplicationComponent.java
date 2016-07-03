package com.toan_it.library.library.injector.component;

import com.toan_it.library.library.data.local.DatabaseRealm;
import com.toan_it.library.library.data.local.PreferencesHelper;
import com.toan_it.library.library.data.networking.RestApi;
import com.toan_it.library.library.data.networking.RestData;
import com.toan_it.library.library.data.rxjava.RxBus;
import com.toan_it.library.library.injector.module.ApplicationModule;
import com.toan_it.library.library.injector.module.NetworkModule;
import com.toan_it.library.library.libs.image.ImageLoaderListener;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = { ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    RestData mRestData();

    RxBus mRxBus();

    PreferencesHelper mPreferencesHelper();

    DatabaseRealm mDatabaseRealm();

    RestApi mRestApi();

    ImageLoaderListener mImageLoader();

    OkHttpClient mOkHttpClient();
}