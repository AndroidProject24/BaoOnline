package com.toan_it.library.library;

import com.facebook.stetho.Stetho;
import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.toan_it.library.BuildConfig;
import com.toan_it.library.library.utils.ToastUtils;
import com.toan_it.library.skinloader.base.SkinBaseApplication;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:20:59
 */
public class BaseApplication extends SkinBaseApplication {
    private static BaseApplication mInstance;
    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ToastUtils.init(this);
        debug();
    }
    private void debug(){
        if (BuildConfig.DEBUG) {
            //AndroidDevMetrics.initWith(this);
            refWatcher = LeakCanary.install(this);
            Stetho.initializeWithDefaults(this);
            BlockCanary.install(this, new AppBlockCanaryContext()).start();
        }
    }
    public static BaseApplication getInstance() {
        return mInstance;
    }

    public static RefWatcher getRefWatcher() {
        return BaseApplication.getInstance().refWatcher;
    }
}
