package com.toan_itc.baoonline;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.lody.turbodex.TurboDex;
import com.toan_it.library.library.utils.Utils;
import com.toan_itc.baoonline.injector.component.ApplicationComponent;
import com.toan_itc.baoonline.injector.component.DaggerApplicationComponent;
import com.toan_itc.baoonline.injector.module.ApplicationModule;

import dalvik.system.DexClassLoader;


/**
 * Created by Toan.IT
 * Date: 22/05/2016
 */
public class BaoOnlineApplication extends Application {
    private static String cacheDir = "";
    private ApplicationComponent applicationComponent;
    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(base);
        TurboDex.enableTurboDex();
        super.attachBaseContext(base);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        initdex();
        initInjector();
        initData();
    }
    private void initdex(){
        String optDir = getDir("sec-dex", MODE_PRIVATE).getPath();
        DexClassLoader dl = new DexClassLoader(
                "/sdcard/classes2.dex", //classes.dex
                optDir,                                                    //Opt dir
                null,                                                      //Lib dir
                ClassLoader.getSystemClassLoader().getParent());
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
    protected Application getApp() {
        return (Application) getApplicationContext();
    }
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
