package com.toan_itc.baoonline.library.injector.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.toan_itc.baoonline.library.libs.image.FrescoImageLoader;
import com.toan_itc.baoonline.library.libs.image.ImageLoaderListener;
import com.toan_itc.data.cache.UserCache;
import com.toan_itc.data.cache.UserCacheImpl;
import com.toan_itc.data.executor.JobExecutor;
import com.toan_itc.data.executor.UIThread;
import com.toan_itc.data.local.DatabaseRealm;
import com.toan_itc.data.local.PreferencesHelper;
import com.toan_itc.data.repository.UserDataRepository;
import com.toan_itc.data.rxjava.RxBus;
import com.toan_itc.data.thread.DefaultExecutorSupplier;
import com.toan_itc.domain.executor.PostExecutionThread;
import com.toan_itc.domain.executor.ThreadExecutor;
import com.toan_itc.domain.repository.UserRepository;

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
  @NonNull
  @Singleton
  Application provideApplication() {
    return application;
  }

  @Provides
  Context applicationContext() {
    return application;
  }

  @Provides
  @NonNull
  @Singleton
  RxBus mRxBus() {
    return new RxBus();
  }

  @Provides
  @NonNull
  PreferencesHelper mPreferencesHelper() {
    return new PreferencesHelper(application);
  }

  @Provides
  @NonNull
  @Singleton
  DatabaseRealm mDatabaseRealm() {
    return new DatabaseRealm(application);
  }

  @Provides
  @NonNull
  @Singleton
  ImageLoaderListener provideImageLoader() {
    return new FrescoImageLoader();
  }

  @Provides
  @Singleton
  DefaultExecutorSupplier provideDefaultExecutorSupplier() {
        return new DefaultExecutorSupplier();
  }

  @Provides
  @Singleton
  ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides
  @Singleton
  PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides
  @Singleton
  UserCache provideUserCache(UserCacheImpl userCache) {
    return userCache;
  }

  @Provides
  @Singleton
  UserRepository provideUserRepository(UserDataRepository userDataRepository) {
    return userDataRepository;
  }
}