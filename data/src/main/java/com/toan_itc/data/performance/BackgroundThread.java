package com.toan_itc.data.performance;

import android.os.HandlerThread;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * Created by Toan.IT
 * Date: 16/08/2016
 * Email: huynhvantoan.itc@gmail.com
 */
public class BackgroundThread extends HandlerThread {
    public BackgroundThread() {
        super("BackgroundThread", THREAD_PRIORITY_BACKGROUND);
    }
}