package com.toan_itc.baoonline.injector.module;

import android.app.Application;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.libs.reactivenetwork.ReactiveNetwork;
import com.toan_itc.data.local.preferences.PreferencesHelper;
import com.toan_itc.data.local.realm.RealmManager;
import com.toan_itc.data.network.RestApi;
import com.toan_itc.data.repository.disk.DiskDataSource;
import com.toan_itc.data.repository.remote.CloudDataSource;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Toan.IT
 * Date: 07/09/2016
 * Email: huynhvantoan.itc@gmail.com
 */
@Module
public class DataModule {

  private Application application;

  public DataModule(Application application) {
    this.application = application;
  }

  @Provides
  Gson provideGson() {
    return new Gson();
  }

  @Provides
  @NonNull
  PreferencesHelper mPreferencesHelper() {
    return new PreferencesHelper(application);
  }

  @Provides
  @Singleton
  RealmManager mRealmManager() {
    return new RealmManager();
  }

  @Provides
  @Singleton
  DiskDataSource mDiskDataSource(RealmManager realm) {
    return new DiskDataSource(realm);
  }

  @Provides
  @Singleton
  CloudDataSource mCloudDataSource(DiskDataSource diskDataSource,RestApi restApi, ReactiveNetwork reactiveNetwork, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    return new CloudDataSource(diskDataSource,restApi,reactiveNetwork,threadExecutor,postExecutionThread);
  }
}
