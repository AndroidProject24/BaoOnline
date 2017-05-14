package com.toan_itc.data.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * by Toan.IT on 2016/5/15.
 */
@SuppressWarnings("ALL")
public class TimeUtils {

    private static SimpleDateFormat format;

    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDate() {
        Date d = new Date();
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(d);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDateToString(long time) {
        Date d = new Date(time);
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(d);
    }

    public static String timeDiff(long time){
        long diffMinute =(System.currentTimeMillis()-time)/60000;
        if (diffMinute < 60){
            return diffMinute+" minutes ago";
        }else if (diffMinute < 24*60){
            return diffMinute/60 +" hours ago";
        }else if (diffMinute < 24*60*30){
            return diffMinute/60/24 +" days ago";
        }else {
            return diffMinute/60/24/30 +" months ago";
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String getStringToDate(String time) {
        if(time.endsWith("GMT")) {
            //Logger.e("getStringToDate"+"11111");
            format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        }else if(time.endsWith("(GMT+7)")) {
            //Logger.e("getStringToDate"+"2222");
            time = time.substring(0, 10);
            format = new SimpleDateFormat("dd/MM/yyyy");
        }else if(time.length()==19){
            //Logger.e("getStringToDate"+"33333");
            if(time.substring(0,time.indexOf("-")).length()>2)
                format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
            else if(time.substring(0,time.indexOf("-")).length()<4)
                format = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
            else
                format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
        }else{
           // Logger.e("getStringToDate"+"44444");
            format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
        }
        Date date = new Date();
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getTime(date,format);
    }
    private static String getTime(Date date,DateFormat dateFormat){
        String getdate = dateFormat.format(new Date());
        String time = null;
        try {
            Date date2 = dateFormat.parse(getdate);
            long diff = date2.getTime() - date.getTime();
            long diffseconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long housre = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            if(diffDays > 1)
                time =diffDays + " Ngày trước";
            else if (diffDays == 1)
                time ="1 Ngày "+ housre + " Giờ trước";
            else if (housre > 1)
                time =housre + " Giờ "+diffMinutes + " Phút trước";
            else if (housre == 1)
                time = "1 Giờ " + diffMinutes + " Phút trước";
            else if (diffMinutes > 1)
                time =diffMinutes + " Phút trước";
            else  if (diffMinutes == 1)
                time = "1 Phút " + diffseconds + " Giây trước";
            else
                time = "Vừa mới đây";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }
}
