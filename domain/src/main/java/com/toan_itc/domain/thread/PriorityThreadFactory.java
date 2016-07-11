package com.toan_itc.domain.thread;

import android.os.Process;
import java.util.concurrent.ThreadFactory;
/**
 * Created by Toan.IT
 * Date: 11/07/2016
 */

public class PriorityThreadFactory implements ThreadFactory {

    private final int mThreadPriority;

    public PriorityThreadFactory(int threadPriority) {
        mThreadPriority = threadPriority;
    }

    @Override
    public Thread newThread(final Runnable runnable) {
        Runnable wrapperRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Process.setThreadPriority(mThreadPriority);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
                runnable.run();
            }
        };
        return new Thread(wrapperRunnable);
    }

}