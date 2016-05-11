package com.toan_itc.baoonline;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.common.LogUtil;
import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.toan_itc.baoonline.injector.component.ApplicationComponent;
import com.toan_itc.baoonline.injector.component.DaggerApplicationComponent;
import com.toan_itc.baoonline.injector.module.ApplicationModule;
import com.toan_itc.baoonline.utils.Utils;

import timber.log.Timber;

/**
 * Created by Toan.IT on 5/1/16.
 */
public class BaoOnlineApplication extends Application {
    public static String cacheDir = "";
    private ApplicationComponent applicationComponent;
    private RefWatcher refWatcher;
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext=this;
        initInjector();
        initData();
        debug();
    }
    private void debug(){
        if (BuildConfig.DEBUG) {
            //AndroidDevMetrics.initWith(this);
            refWatcher = LeakCanary.install(this);
            Stetho.initializeWithDefaults(this);
            BlockCanary.install(this, new AppBlockCanaryContext()).start();
            Timber.plant(new Timber.DebugTree());
        }
    }
    private void initInjector(){
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }
    private void initData(){
        try {
            if (getApplicationContext().getExternalCacheDir() != null && Utils.ExistSDCard()) {
                cacheDir = getApplicationContext().getExternalCacheDir().toString();
            } else {
                cacheDir = getApplicationContext().getCacheDir().toString();
            }
        } catch (Exception e) {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }
    }
    public static BaoOnlineApplication get(Context context) {
        return (BaoOnlineApplication) context.getApplicationContext();
    }
    public static Context getAppContext() {
        return sContext;
    }
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static RefWatcher getRefWatcher(Context context) {
        BaoOnlineApplication application = (BaoOnlineApplication) context.getApplicationContext();
        return application.refWatcher;
    }
}
