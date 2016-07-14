package com.toan_itc.baoonline.library.injector.component;

import android.content.Context;

import com.toan_itc.baoonline.library.base.BaseActivity;
import com.toan_itc.baoonline.library.injector.module.ApplicationModule;
import com.toan_itc.baoonline.library.injector.module.NetworkModule;
import com.toan_itc.baoonline.library.libs.image.ImageLoaderListener;
import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.local.DatabaseRealm;
import com.toan_itc.data.local.PreferencesHelper;
import com.toan_itc.data.network.RestApi;
import com.toan_itc.data.rxjava.RxBus;
import com.toan_itc.data.thread.DefaultExecutorSupplier;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = { ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    Context context();

    //RestData mRestData();

    RxBus mRxBus();

    PreferencesHelper mPreferencesHelper();

    DatabaseRealm mDatabaseRealm();

    RestApi mRestApi();

    ImageLoaderListener mImageLoader();

    OkHttpClient mOkHttpClient();

    DefaultExecutorSupplier threadExecutorPriority();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();
}