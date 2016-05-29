package com.toan_it.library.library.performance;

import android.os.HandlerThread;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * Created by Toan.IT on 4/21/16.
 */
public class BackgroundThread extends HandlerThread {
    public BackgroundThread() {
        super("BackgroundThread", THREAD_PRIORITY_BACKGROUND);
    }
}