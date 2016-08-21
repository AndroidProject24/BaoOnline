package com.toan_itc.baoonline.library.injector.component;

import com.toan_itc.baoonline.library.BaseApplication;
import com.toan_itc.baoonline.library.base.BaseActivity;
import com.toan_itc.baoonline.library.base.BaseFragment;
import com.toan_itc.baoonline.library.injector.module.ApplicationModule;
import com.toan_itc.baoonline.library.injector.module.DataModule;
import com.toan_itc.baoonline.library.injector.module.NetworkModule;
import com.toan_itc.baoonline.library.injector.module.ThreadingModule;
import com.toan_itc.data.libs.image.ImageLoaderListener;
import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.repository.Repository;
import com.toan_itc.data.rxjava.RxBus;
import com.toan_itc.data.thread.DefaultExecutorSupplier;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, DataModule.class, ThreadingModule.class})
public interface ApplicationComponent{

    void inject(BaseApplication baseApplication);

    void inject(BaseActivity baseActivity);

    void inject(BaseFragment baseFragment);

    RxBus mRxBus();

    Repository mRepository();

    ImageLoaderListener mImageLoader();

    OkHttpClient mOkHttpClient();

    DefaultExecutorSupplier threadExecutorPriority();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();
}