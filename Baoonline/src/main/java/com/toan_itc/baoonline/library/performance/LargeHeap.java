package com.toan_itc.baoonline.library.performance;

import android.os.Debug;

import java.util.Locale;

/**
 * Created by huynh
 * Date: 22/05/2016
 */
public class LargeHeap {
    private PerfListener mPerfListener;
    private static final long STATS_CLOCK_INTERVAL_MS = 1000;
    private static final int DEFAULT_MESSAGE_SIZE = 1024;
    private static final int BYTES_IN_MEGABYTE = 1024 * 1024;
    public void updateStats() {
        mPerfListener = new PerfListener();
        final Runtime runtime = Runtime.getRuntime();
        final long heapMemory = runtime.totalMemory() - runtime.freeMemory();
        final StringBuilder sb = new StringBuilder(DEFAULT_MESSAGE_SIZE);
        // When changing format of output below, make sure to sync "run_comparison.py" as well
        sb.append("Heap: ");
        appendSize(sb, heapMemory);
        sb.append(" Java ");
        appendSize(sb, Debug.getNativeHeapSize());
        sb.append(" native\n");
        /*appendTime(sb, "Avg wait time: ", mPerfListener.getAverageWaitTime(), "\n");
        appendNumber(sb, "Requests: ", mPerfListener.getOutstandingRequests(), " outsdng ");
        appendNumber(sb, "", mPerfListener.getCancelledRequests(), " cncld\n");*/
        final String message = sb.toString();
    //    Timber.e(layout_error);
    }

    private static void appendSize(StringBuilder sb, long bytes) {
        String value = String.format(Locale.getDefault(), "%.1f MB", (float) bytes / BYTES_IN_MEGABYTE);
        sb.append(value);
    }

    private static void appendTime(StringBuilder sb, String prefix, long timeMs, String suffix) {
        appendValue(sb, prefix, timeMs + " ms", suffix);
    }

    private static void appendNumber(StringBuilder sb, String prefix, long number, String suffix) {
        appendValue(sb, prefix, number + "", suffix);
    }

    private static void appendValue(StringBuilder sb, String prefix, String value, String suffix) {
        sb.append(prefix).append(value).append(suffix);
    }
}
