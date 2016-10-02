package com.toan_itc.baoonline.library.injector.module;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.toan_itc.data.local.preferences.PreferencesHelper;
import com.toan_itc.data.local.realm.RealmManager;
import com.toan_itc.data.repository.disk.DiskDataSource;
import com.toan_itc.data.repository.memory.MemoryDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
		return new RealmManager(application);
	}

	@Provides
	@Singleton
	DiskDataSource mDiskData(RealmManager realm) {
		return new DiskDataSource(realm);
	}

	@Singleton
	@Provides
	MemoryDataSource mMemoryDataSource() {
		return new MemoryDataSource();
	}
}
