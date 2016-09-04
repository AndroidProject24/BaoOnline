package com.toan_itc.baoonline.library;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.github.moduth.blockcanary.BlockCanaryContext;
import com.toan_itc.baoonline.BuildConfig;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 22/05/2016
 */
public class AppBlockCanaryContext extends BlockCanaryContext {
    private static final String TAG = "AppContext";

    @Override
    public String provideQualifier() {
        String qualifier = "";
        try {
            PackageInfo info = BaseApplication.getInstance().getPackageManager().getPackageInfo(BaseApplication.getInstance().getPackageName(), 0);
            qualifier += info.versionCode + "_" + info.versionName + "_YYB";
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "getQualifier exception", e);
        }
        return qualifier;
    }

    @Override
    public String provideUid() {
        return "87224330";
    }

    @Override
    public String provideNetworkType() {
        return "4G";
    }

    @Override
    public int provideMonitorDuration() {
        return 9999;
    }

    @Override
    public int provideBlockThreshold() {
        return 500;
    }

    @Override
    public boolean displayNotification() {
        return BuildConfig.DEBUG;
    }

    @Override
    public List<String> concernPackages() {
        List<String> list = super.provideWhiteList();
        list.add("com.example");
        return list;
    }

    @Override
    public List<String> provideWhiteList() {
        List<String> list = super.provideWhiteList();
        list.add("com.whitelist");
        return list;
    }
}