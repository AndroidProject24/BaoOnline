package com.toan_itc.data.thread;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * Created by Toan.IT
 * Date: 11/07/2016
 */

public class MainThreadExecutor implements Executor {

    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(@NonNull Runnable runnable) {
        handler.post(runnable);
    }
}