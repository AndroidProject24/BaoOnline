package com.toan_itc.baoonline.library.injector.component;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.toan_itc.baoonline.library.BaseApplication;
import com.toan_itc.baoonline.library.base.BaseFragment;
import com.toan_itc.baoonline.library.injector.module.ApplicationModule;
import com.toan_itc.baoonline.library.injector.module.NetworkModule;
import com.toan_itc.baoonline.library.libs.image.ImageLoaderListener;
import com.toan_itc.baoonline.navigation.Navigator;
import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.rxjava.RxBus;
import com.toan_itc.data.thread.DefaultExecutorSupplier;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = { ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent extends RealmComponent{

    void inject(BaseApplication application);

    void inject(AppCompatActivity activity);

    void inject(BaseFragment fragment);

    Context context();

    Navigator navigator();

    RxBus mRxBus();

    ImageLoaderListener mImageLoader();

    OkHttpClient mOkHttpClient();

    DefaultExecutorSupplier threadExecutorPriority();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();
}