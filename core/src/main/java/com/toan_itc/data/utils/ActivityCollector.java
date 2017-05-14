package com.toan_itc.data.utils;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Toan.IT on 2016/9/22.
 * huynhvantoan.itc@gmail.com
 */
public final class ActivityCollector {

  private ActivityCollector() {
    throw new RuntimeException("ActivityCollector cannot be initialized!");
  }

  private static Set<Activity> allActivities;

  public static void addActivity(Activity activity) {
    Preconditions.checkNotNull(activity,"Add activity not null!");
    if (allActivities == null) {
      allActivities = new HashSet<>();
    }
    allActivities.add(activity);
  }

  public static void removeActivity(Activity activity) {
    Preconditions.checkNotNull(activity,"Remove activity not null!");
    allActivities.remove(activity);
  }

  @SuppressWarnings("SynchronizeOnNonFinalField")
  public static void finishAll() {
    Preconditions.checkNotNull(allActivities,"ListActivity not null!");
    synchronized (allActivities) {
      for (Activity activity : allActivities) {
        if (!activity.isFinishing()) {
          activity.finish();
        }
      }
    }
    android.os.Process.killProcess(android.os.Process.myPid());
    System.exit(0);
  }
}
