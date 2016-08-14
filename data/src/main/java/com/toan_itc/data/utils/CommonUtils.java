package com.toan_itc.data.utils;

/**
 * Created by Toan.IT
 * Date: 14/08/2016
 * Email: huynhvantoan.itc@gmail.com
 */

public class CommonUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("");
    }
}
