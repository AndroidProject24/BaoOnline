package com.toan_itc.data.utils;

import android.text.TextUtils;

import static android.os.Looper.getMainLooper;
import static android.os.Looper.myLooper;

/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */

public class LoggerSimple {
    private static String sTag = "";
    private static boolean sDebug = true;

    public static void initTag(String tag) {
        initTag(tag, true);
    }

    private static void initTag(String tag, boolean debug) {
        sTag = tag;
        sDebug = debug;
    }

    public static void d(String str, Object... args) {
        if (sDebug || isDebug()) {
            android.util.Log.d(getTag(), buildLogString(str, args));
        }
    }

    public static void e(String str, Object... args) {
        if (sDebug || isDebug()) {
            android.util.Log.e(getTag(), buildLogString(str, args));
        }
    }
    private static String getTag() {
        if (!TextUtils.isEmpty(sTag)) {
            return sTag;
        }
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
        return caller.getFileName();
    }

    private static String buildLogString(String str, Object... args) {
        if (args.length > 0) {
            str = String.format(str, args);
        }
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("(")
                .append(caller.getFileName())
                .append(":")
                .append(caller.getLineNumber())
                .append(").")
                .append(caller.getMethodName())
                .append("():")
                .append(str)
                .append("[MainThread:")
                .append(getMainLooper() == myLooper())
                .append("]");
        return stringBuilder.toString();
    }

    private static boolean isDebug() {
        return android.util.Log.isLoggable(getTag(), android.util.Log.DEBUG);
    }
}
