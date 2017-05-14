package com.toan_itc.data.exception;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Toan.IT
 * Date: 18/08/2016
 * Email: huynhvantoan.itc@gmail.com
 */

public class CrashException implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler mPrevious;
    private String ANDROID = Build.VERSION.RELEASE;
    private String MODEL = Build.MODEL;
    private String MANUFACTURER = Build.MANUFACTURER;
    private static String VERSION = "Unknown";
    private static CrashException.CarshBuilder mBuilder;
    private boolean isAppend;
    private boolean isSimple;
    public void setSimple(boolean isSimple) {
        this.isSimple = isSimple;
    }
    public CrashException setAppend(boolean isAppend) {
        this.isAppend = isAppend;
        return this;
    }

    private CrashException() {
        mPrevious = Thread.currentThread().getUncaughtExceptionHandler();
        Thread.currentThread().setUncaughtExceptionHandler(this);
    }

    public static CrashException init(Context context, String dirName) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            VERSION = info.versionName + info.versionCode;
            mBuilder = CrashException.CarshBuilder.build(context, dirName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new CrashException();
    }

    private static String formatNumber(int value) {
        return new DecimalFormat("00").format(value);
    }

    private static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        return calendar.get(Calendar.YEAR) + "-" + formatNumber((calendar.get(Calendar.MONTH) + 1)) + "-"
                + formatNumber(calendar.get(Calendar.DAY_OF_MONTH)) + "  "
                + formatNumber(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + formatNumber(calendar.get(Calendar.MINUTE));
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        try {
            dumpExceptionToSDCard(throwable);
            uploadExceptionToServer();
            if (mPrevious != null) {
                mPrevious.uncaughtException(thread, throwable);
            }else{
                new Thread(() -> {
                    Looper.prepare();

                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);

                    Looper.loop();
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class CarshBuilder {
        private String carsh_dir;

        private String getCarsh_dir() {
            return carsh_dir;
        }

        private String getCarsh_log() {
            return getCarsh_dir() + File.separator + "carshRecord.log";
        }

        private String getCarsh_tag() {

            return getCarsh_dir() + File.separator + ".carshed";
        }

        private CarshBuilder(Context context, String dirName) {
            if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                this.carsh_dir = context.getCacheDir().getPath() + File.separator + dirName;
            } else
                this.carsh_dir = Environment.getExternalStorageDirectory().getPath() + File.separator + dirName;
        }

        public static CrashException.CarshBuilder build(Context context, String dirName) {
            return new CrashException.CarshBuilder(context, dirName);
        }

        @Override
        public String toString() {
            return "CarshBuilder [dir path: " + getCarsh_dir() + "-- log path:" + getCarsh_log() + "-- tag path:" + getCarsh_tag() + "]";
        }
    }

    private static String getLogFilePath() {
        if (mBuilder == null)
            return "Unknown";
        else
            return mBuilder.getCarsh_log();
    }

    private void dumpExceptionToSDCard(Throwable ex) throws IOException {
        File f = new File(mBuilder.getCarsh_log());
        if (f.exists()) {
            if (!isAppend)
                f.delete();
        } else {
            try {
                new File(mBuilder.getCarsh_dir()).mkdirs();
                f.createNewFile();
            } catch (Exception e) {
                return;
            }
        }

        PrintWriter p;
        try {
            p = new PrintWriter(new FileWriter(f, true));
        } catch (Exception e) {
            return;
        }
        p.write("\n*************---------Carsh  Log  Head ------------****************\n\n");
        p.write("Happend Time: " + getCurrentDate() + "\n");
        p.write("Android Version: " + ANDROID + "\n");
        p.write("Device Model: " + MODEL + "\n");
        p.write("Device Manufacturer: " + MANUFACTURER + "\n");
        p.write("App Version: v" + VERSION + "\n\n");
        p.write("*************---------Carsh  Log  Head ------------****************\n\n");
        if (!isSimple)
            ex.printStackTrace(p);
        else {
            p.write(ex.getLocalizedMessage() + "\n");
        }
        p.close();
        try {
            new File(mBuilder.getCarsh_tag()).createNewFile();
        } catch (Exception e) {
            return;
        }
    }

    private void uploadExceptionToServer() {
        //TODO Upload Exception Message To Your Web Server
    }

    public static String getLogContent() {
        if (TextUtils.isEmpty(getLogFilePath()))
            return null;

        File file = new File(getLogFilePath());
        if (file.exists() && file.isFile()) {
            BufferedReader bis = null;
            try {
                bis = new BufferedReader(new FileReader(file));
                String buffer = null;
                StringBuilder sb = new StringBuilder();
                while ((buffer = bis.readLine()) != null) {
                    sb.append(buffer);
                }
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bis != null)
                        bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
