package com.toan_it.library.skinloader.statusbar;

import android.app.Activity;
import android.os.Build;

/**
 * Created by Toan.IT
 * Date: 22/05/2016
 */
public class StatusBarBackground {
    private Activity activity;
    private int color;
    public StatusBarBackground(Activity activity, int color) {

        this.activity = activity;
        this.color = color;
    }

    public void setStatusBarbackColor()//android:fitsSystemWindows="true"
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusBarUtil.setTransparent(activity);
        }
    }
}
