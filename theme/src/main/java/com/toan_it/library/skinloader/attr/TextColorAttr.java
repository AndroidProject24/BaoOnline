package com.toan_it.library.skinloader.attr;

import android.view.View;
import android.widget.TextView;

import com.toan_it.library.skinloader.load.SkinManager;
import com.toan_it.library.skinloader.util.L;

/**
 * Created by _SOLID
 * Date:2016/4/13
 * Time:22:53
 */
public class TextColorAttr extends SkinAttr {
    @Override
    public void apply(View view) {
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            if (RES_TYPE_NAME_COLOR.equals(attrValueTypeName)) {
                tv.setTextColor(SkinManager.getInstance().convertToColorStateList(attrValueRefId));
            }
        }
    }
}
