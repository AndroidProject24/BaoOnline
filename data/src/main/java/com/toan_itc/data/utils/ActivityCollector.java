package com.toan_itc.data.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toan.IT on 2016/9/22.
 * huynhvantoan.itc@gmail.com
 */
public final class ActivityCollector {

    private ActivityCollector() {
        throw new RuntimeException("ActivityCollector cannot be initialized!");
    }

    private static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
