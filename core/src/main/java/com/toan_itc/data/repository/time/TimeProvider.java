package com.toan_itc.data.repository.time;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Calendar;

public class TimeProvider {
    //Calculator time get data
    //User Game-of-Thrones
    private static final String KEY = "persisted_time_key";
    private Context context;

    public TimeProvider(Context context) {
        this.context = context;
    }

    public void persistTime() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit()
                .putLong(KEY, System.currentTimeMillis())
                .apply();
    }

    public long getPersistedTime() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getLong(KEY, getMyBirthday());
    }

    private long getMyBirthday() {
        Calendar cal = Calendar.getInstance();
        cal.set(1993, Calendar.JANUARY, 2);
        return cal.getTimeInMillis();
    }

}
