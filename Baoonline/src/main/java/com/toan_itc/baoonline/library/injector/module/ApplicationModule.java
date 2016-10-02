package com.toan_itc.baoonline.library.injector.module;

import android.app.Application;
import android.content.Context;

import com.toan_itc.baoonline.library.injector.qualifier.AppContext;
import com.toan_itc.data.libs.image.FrescoImageLoader;
import com.toan_itc.data.libs.image.ImageLoaderListener;
import com.toan_itc.data.rxjava.RxBus;
import com.toan_itc.data.rxjava.RxUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

	protected final Application application;

	public ApplicationModule(Application application) {
		this.application = application;
	}

	@Provides
	@Singleton
	Application provideApplication() {
		return application;
	}

	@Provides
	@AppContext
	Context provideContext() {
		return application;
	}

	@Provides
	@Singleton
	RxBus mRxBus() {
		return new RxBus();
	}

	@Provides
	@Singleton
	RxUtils mRxUtils() {
		return new RxUtils();
	}

	@Provides
	@Singleton
	ImageLoaderListener provideImageLoader() {
		return new FrescoImageLoader();
	}

}