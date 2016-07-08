package com.toan_itc.baoonline.library.injector.component;

import com.toan_itc.baoonline.injector.ActivityComponent;
import com.toan_itc.baoonline.library.data.local.DatabaseRealm;
import com.toan_itc.baoonline.library.data.local.PreferencesHelper;
import com.toan_itc.baoonline.library.data.networking.RestApi;
import com.toan_itc.baoonline.library.data.networking.RestData;
import com.toan_itc.baoonline.library.data.rxjava.RxBus;
import com.toan_itc.baoonline.library.injector.module.ActivityModule;
import com.toan_itc.baoonline.library.injector.module.ApplicationModule;
import com.toan_itc.baoonline.library.injector.module.NetworkModule;
import com.toan_itc.baoonline.library.libs.image.ImageLoaderListener;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = { ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    ActivityComponent plus(ActivityModule module);

    RestData mRestData();

    RxBus mRxBus();

    PreferencesHelper mPreferencesHelper();

    DatabaseRealm mDatabaseRealm();

    RestApi mRestApi();

    ImageLoaderListener mImageLoader();

    OkHttpClient mOkHttpClient();
}