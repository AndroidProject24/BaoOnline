package com.toan_itc.baoonline.injector.component;

import android.content.Context;

import com.toan_itc.baoonline.injector.module.ApplicationModule;
import com.toan_itc.baoonline.injector.module.DataModule;
import com.toan_itc.baoonline.injector.module.NetworkModule;
import com.toan_itc.baoonline.injector.module.ThreadingModule;
import com.toan_itc.baoonline.injector.qualifier.AppContext;
import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.libs.image.ImageLoaderListener;
import com.toan_itc.data.local.realm.RealmManager;
import com.toan_itc.data.repository.Repository;
import com.toan_itc.data.rxjava.RxBus;
import com.toan_itc.data.rxjava.RxUtils;
import com.toan_itc.data.thread.DefaultExecutorSupplier;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, DataModule.class, ThreadingModule.class})
public interface ApplicationComponent {

	//void inject(BaseApplication baseApplication);

	//void inject(SimpleInjectActivity simpleInjectActivity);

	//void inject(BaseFragment baseFragment);

	@AppContext
	Context context();

	RxBus mRxBus();

	RxUtils mRxUtils();

	Repository mRepository();

	ImageLoaderListener mImageLoader();

	OkHttpClient mOkHttpClient();

	DefaultExecutorSupplier threadExecutorPriority();

	ThreadExecutor threadExecutor();

	PostExecutionThread postExecutionThread();

	RealmManager realm();
}