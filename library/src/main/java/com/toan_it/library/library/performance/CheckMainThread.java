package com.toan_it.library.library.performance;

import android.os.Looper;

/**
 * Created by Toan.IT on 4/21/16.
 */
public class CheckMainThread {
    public static boolean isInMainThread() {
        Looper myLooper = Looper.myLooper();
        Looper mainLooper = Looper.getMainLooper();
        return myLooper == mainLooper;
    }
}
