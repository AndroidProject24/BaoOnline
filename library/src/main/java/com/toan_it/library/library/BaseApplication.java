package com.toan_it.library.library;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.frogermcs.androiddevmetrics.AndroidDevMetrics;
import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.toan_it.library.BuildConfig;
import com.toan_it.library.library.injector.component.ApplicationComponent;
import com.toan_it.library.library.injector.component.DaggerApplicationComponent;
import com.toan_it.library.library.injector.module.ApplicationModule;
import com.toan_it.library.library.injector.module.NetworkModule;
import com.toan_it.library.library.utils.Utils;
import com.toan_it.library.skinloader.base.SkinBaseApplication;

import java.io.File;

import dalvik.system.DexClassLoader;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:20:59
 */
public class BaseApplication extends SkinBaseApplication {
    private static BaseApplication mInstance;
    private RefWatcher refWatcher;
    private ApplicationComponent applicationComponent;
    private File cacheDir =null;
    @Override
    protected void attachBaseContext(Context base) {
       // MultiDex.install(base);
      //  TurboDex.enableTurboDex();
        super.attachBaseContext(base);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        initCache();
        initInjector();
        initFresco();
        //initdex();
        mInstance = this;
        injectDebug();
    }
    private void initFresco(){
        Fresco.initialize(this);
    }
    private void injectDebug(){
        if (BuildConfig.DEBUG) {
            AndroidDevMetrics.initWith(this);
            refWatcher = LeakCanary.install(this);
            Stetho.initializeWithDefaults(this);
            BlockCanary.install(this, new AppBlockCanaryContext()).start();
        }
    }
    private void initInjector(){
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(cacheDir,true))
                .build();
    }
    private void initdex(){
        String optDir = getDir("sec-dex", MODE_PRIVATE).getPath();
        DexClassLoader dl = new DexClassLoader(
                "/sdcard/classes2.dex", //classes.dex
                optDir,                                                    //Opt dir
                null,                                                      //Lib dir
                ClassLoader.getSystemClassLoader().getParent());
    }
    private void initCache(){
        try {
            if (getApplicationContext().getExternalCacheDir() != null && Utils.ExistSDCard()) {
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
        return BaseApplication.getInstance().refWatcher;
    }

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }
    protected Application getApp() {
        return (Application) getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
