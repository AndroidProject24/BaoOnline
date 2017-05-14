package com.toan_itc.data.performance;

import android.util.Log;

import com.toan_itc.data.BuildConfig;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Toan.IT
 * Date: 23/08/2016
 * Email: huynhvantoan.itc@gmail.com
 */
public class PerformanceLog {

   private static final String TAG = PerformanceLog.class.getSimpleName();
   private static final long MILLI = 1000000;
   private static final NumberFormat FORMAT = new DecimalFormat("#,###,###");

   private static final Map<String, PerformanceLog> LOGGERS = new HashMap<>(3);

   public static void start(String id) {
      if (!BuildConfig.DEBUG) return;
      if (LOGGERS.containsKey(id)) {
         LOGGERS.remove(id);
      }
      LOGGERS.put(id, new PerformanceLog(id));
   }

   public static void stop(String id) {
      if (!BuildConfig.DEBUG) return;
      if (LOGGERS.containsKey(id)) {
         LOGGERS.remove(id).finish();
      }
   }

   private final String id;
   private long startTime;

   private PerformanceLog(String id) {
      this.id = id;
      startTime = System.nanoTime();
   }

   private void finish() {
      long elapsed = System.nanoTime() - startTime;

      if (elapsed > 5 * MILLI) {
         Log.wtf(TAG, id + ": " + (elapsed / MILLI) + " mili");
      } else {
         Log.wtf(TAG, id + ": " + FORMAT.format(elapsed) + " nano");
      }
   }
   //Use
   //PerformanceLog.stop("String");
   //PerformanceLog.start("Realm processing");
}
