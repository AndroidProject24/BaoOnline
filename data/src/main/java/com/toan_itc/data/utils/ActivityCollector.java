package com.toan_itc.data.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Toan.IT on 2016/9/22.
 * huynhvantoan.itc@gmail.com
 */
public final class ActivityCollector {

    private ActivityCollector() {
        throw new RuntimeException("ActivityCollector cannot be initialized!");
    }

    private static Stack<Activity> activities;

    public static void addActivity(Activity activity) {
        Preconditions.checkNotNull(activity,"Add activity not null!");
	    if (activities == null) {
		    activities = new Stack<Activity>();
	    }
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        Preconditions.checkNotNull(activity,"Remove activity not null!");
        activities.remove(activity);
    }

    public static void finishAll() {
	    Preconditions.checkNotNull(activities,"ListActivity not null!");
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
