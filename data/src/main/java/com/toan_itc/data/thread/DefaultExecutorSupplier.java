package com.toan_itc.data.thread;

import android.os.Build;
import android.os.Process;

import com.toan_itc.data.thread.priority.PriorityThreadPoolExecutor;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Created by Toan.IT
 * Date: 11/07/2016
 */

public class DefaultExecutorSupplier{

	/*
	* thread pool executor for priority background tasks
	*/
    private final PriorityThreadPoolExecutor mForBackgroundPriorityTasks;
    /*
    * thread pool executor for background tasks
    */
    private final ThreadPoolExecutor mForBackgroundTasks;
    /*
    * thread pool executor for light weight background tasks
    */
    private final ThreadPoolExecutor mForLightWeightBackgroundTasks;
    /*
    * thread pool executor for main thread tasks
    */
    private final Executor mMainThreadExecutor;
    /*
    * an instance of DefaultExecutorSupplier
    */
   /* private static DefaultExecutorSupplier sInstance;

    public static DefaultExecutorSupplier getInstance() {
        if (sInstance == null) {
            synchronized (DefaultExecutorSupplier.class) {
                sInstance = new DefaultExecutorSupplier();
            }
        }
        return sInstance;
    }*/
    /*
    * constructor for  DefaultExecutorSupplier
    */
        public DefaultExecutorSupplier() {

            // setting the thread factory
            ThreadFactory backgroundPriorityThreadFactory
		            = new PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND);

            // setting the thread pool executor for mForBackgroundPriorityTasks;
	        int NUMBER_OF_CORES = coreNumber();
	        mForBackgroundPriorityTasks = new PriorityThreadPoolExecutor(
                    NUMBER_OF_CORES * 2,
                    NUMBER_OF_CORES * 2,
                    60L,
                    TimeUnit.SECONDS,
                    backgroundPriorityThreadFactory
            );

            // setting the thread pool executor for mForBackgroundTasks;
            mForBackgroundTasks = new ThreadPoolExecutor(
                    NUMBER_OF_CORES * 2,
                    NUMBER_OF_CORES * 2,
                    60L,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(),
                    backgroundPriorityThreadFactory
            );

            // setting the thread pool executor for mForLightWeightBackgroundTasks;
            mForLightWeightBackgroundTasks = new ThreadPoolExecutor(
		            NUMBER_OF_CORES,
		            NUMBER_OF_CORES,
                    60L,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(),
                    backgroundPriorityThreadFactory
            );

            // setting the thread pool executor for mMainThreadExecutor;
            mMainThreadExecutor = new MainThreadExecutor();
        }

    /*
    * returns the thread pool executor for backgroundPriority task
    */
    public PriorityThreadPoolExecutor forBackgroundPriorityTasks() {
        return mForBackgroundPriorityTasks;
    }
    /*
    * returns the thread pool executor for background task
    */
    public ThreadPoolExecutor forBackgroundTasks() {
        return mForBackgroundTasks;
    }

    /*
    * returns the thread pool executor for light weight background task
    */
    public ThreadPoolExecutor forLightWeightBackgroundTasks() {
        return mForLightWeightBackgroundTasks;
    }

    /*
    * returns the thread pool executor for main thread task
    */
    public Executor forMainThreadTasks() {
        return mMainThreadExecutor;
    }

    private int coreNumber() {
        if (Build.VERSION.SDK_INT >= 17) {
            return Runtime.getRuntime().availableProcessors();
        }
        return getNumCoresOldPhones();
    }

    /**
     * Gets the number of cores available in this device, across all processors.
     * Requires: Ability to peruse the filesystem at "/sys/devices/system/cpu"
     *
     * @return The number of cores, or 1 if failed to get result
     */
    private int getNumCoresOldPhones() {
        //Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                //Check if filename is "cpu", followed by a single digit number
                return Pattern.matches("cpu[0-9]+", pathname.getName());
            }
        }

        try {
            //Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            //Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            //Return the number of cores (virtual CPU devices)
            return files.length;
        } catch (Exception e) {
            //Default to return 1 core
            return 1;
        }
    }
}