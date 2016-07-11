package com.toan_itc.baoonline.library;

import android.graphics.Color;
import android.os.Environment;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.stetho.Stetho;
import com.frogermcs.androiddevmetrics.AndroidDevMetrics;
import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.toan_it.library.skinloader.base.SkinBaseApplication;
import com.toan_itc.baoonline.BuildConfig;
import com.toan_itc.baoonline.library.injector.component.ApplicationComponent;
import com.toan_itc.baoonline.library.injector.component.DaggerApplicationComponent;
import com.toan_itc.baoonline.library.injector.module.ApplicationModule;
import com.toan_itc.baoonline.library.injector.module.NetworkModule;

import java.io.File;

import jp.wasabeef.takt.Seat;
import jp.wasabeef.takt.Takt;

/**
 * Created by Toan.IT
 * Date:2016/3/30
 * Time:20:59
 */
public class BaseApplication extends SkinBaseApplication {
    private static BaseApplication mInstance;
    private RefWatcher refWatcher;
    private ApplicationComponent applicationComponent;
    private File cacheDir =null;
    @Override
    public void onCreate() {
        super.onCreate();
        initCache();
        initInjector();
        initFresco();
        mInstance = this;
        injectDebug();
    }
    private void initFresco(){
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(getApplicationContext(), applicationComponent.mOkHttpClient())
                .build();
        Fresco.initialize(getApplicationContext(),config);
    }
    private void injectDebug(){
        if (BuildConfig.DEBUG) {
            AndroidDevMetrics.initWith(this);
            refWatcher = LeakCanary.install(this);
            Stetho.initializeWithDefaults(this);
            BlockCanary.install(this, new AppBlockCanaryContext()).start();
            Takt.stock(this)
                    .seat(Seat.TOP_LEFT)
                    .color(Color.RED)
                    .size(20f)
                    .play();
        }
    }
    private void initInjector(){
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(cacheDir,true))
                .build();
    }
    private void initCache(){
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
        return getInstance().refWatcher;
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
