package com.toan_itc.data.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.LOLLIPOP;

/**
 * Created by AhmedEltaher on 05/12/16.
 */

public class ResourcesUtil {

    public static Drawable getDrawableById(Context context,Resources.Theme theme,int resId) {
        return SDK_INT >= LOLLIPOP ? context.getResources().getDrawable(resId, theme) :
            context.getResources().getDrawable(resId);
    }

    public static String getString(Context context,int resId) {
        return SDK_INT >= LOLLIPOP ? context.getResources().getString(resId) :
            context.getResources().getString(resId);
    }
}
