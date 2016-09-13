package com.toan_itc.data.utils;


/**
 * Created by Toan.IT
 * Date: 05/09/2016
 * Email: huynhvantoan.itc@gmail.com
 */
public class DoubleClickExit {
    private static long lastClick = 0L;
    private static final int THRESHOLD = 2000;

    public static boolean check() {
        long now = System.currentTimeMillis();
        boolean b = now - lastClick < THRESHOLD;
        lastClick = now;
        return b;
    }
}
