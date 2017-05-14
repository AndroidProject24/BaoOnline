package com.toan_itc.baoonline;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.stetho.Stetho;
import com.frogermcs.androiddevmetrics.AndroidDevMetrics;
import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.toan_itc.baoonline.injector.component.ApplicationComponent;
import com.toan_itc.baoonline.injector.component.DaggerApplicationComponent;
import com.toan_itc.baoonline.injector.module.ApplicationModule;
import com.toan_itc.baoonline.injector.module.DataModule;
import com.toan_itc.baoonline.injector.module.NetworkModule;
import com.toan_itc.baoonline.injector.module.ThreadingModule;
import com.toan_itc.data.config.Constants;
import com.toan_itc.data.exception.CrashException;
import com.toan_itc.data.local.realm.RealmManager;
import com.toan_itc.data.local.realm.help.Migration;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import jp.wasabeef.takt.Seat;
import jp.wasabeef.takt.Takt;

/**
 * Created by Toan.IT
 * Date:2016/3/30
 * Time:20:59
 */
public class BaseApplication extends Application {
	private static BaseApplication mInstance;
	private RefWatcher refWatcher;
	private ApplicationComponent applicationComponent;
	private File cacheDir = null;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		initCache();
		initInjector();
		initDatabase();
		initFresco();
		//initDebug();
	}

	private void initDatabase() {
		Realm.init(getApplicationContext());
		RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
						.name(Constants.BaoOnline_Realm)
						.schemaVersion(Constants.RealmVersion)
						.deleteRealmIfMigrationNeeded()
						.migration(new Migration())
						.build();
		Realm.setDefaultConfiguration(realmConfiguration);
		RealmManager.Set_News(this);
	}

	private void initFresco() {
		DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(getApplicationContext())
				.setBaseDirectoryPath(cacheDir)
				.setMaxCacheSize(100)
				.build();
		ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
				.newBuilder(getApplicationContext(), applicationComponent.mOkHttpClient())
				.setBitmapsConfig(Bitmap.Config.RGB_565)
				.setSmallImageDiskCacheConfig(diskCacheConfig)
				.build();
		Fresco.initialize(getApplicationContext(), config);
	}

	private void initDebug() {
		CrashException.init(this, getString(R.string.app_name)).setAppend(true).setSimple(false);
		if (BuildConfig.DEBUG) {
			AndroidDevMetrics.initWith(this);
			refWatcher = LeakCanary.install(this);
			BlockCanary.install(this, new AppBlockCanaryContext()).start();
			Stetho.initialize(Stetho.newInitializerBuilder(this)
					.enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
					//.enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
					.build());
			Takt.stock(this)
					.seat(Seat.TOP_LEFT)
					.color(Color.RED)
					.size(20f)
					.play();
		}
	}

	private void initInjector() {
		if (applicationComponent == null)
			applicationComponent = DaggerApplicationComponent.builder()
					.applicationModule(new ApplicationModule(this))
					.networkModule(new NetworkModule(cacheDir, true))
					.threadingModule(new ThreadingModule())
					.dataModule(new DataModule(this))
					.build();
	}

	private void initCache() {
		try {
			if (getApplicationContext().getExternalCacheDir() != null && Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				cacheDir = getApplicationContext().getExternalCacheDir();
			} else {
				cacheDir = getApplicationContext().getCacheDir();
			}
		} catch (Exception e) {
			cacheDir = getApplicationContext().getCacheDir();
		}
	}

	public static BaseApplication getInstance() {
		return mInstance;
	}

	public static RefWatcher getRefWatcher() {
		if (getInstance().refWatcher != null)
			return getInstance().refWatcher;
		else
			return LeakCanary.install(getInstance());
	}

	public ApplicationComponent getApplicationComponent() {
		return applicationComponent;
	}

	@Override
	public void onTerminate() {
		Takt.finish();
		super.onTerminate();
	}
}
