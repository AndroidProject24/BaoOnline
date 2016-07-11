package com.toan_it.library.skinloader.listener;

import android.view.View;

import com.toan_it.library.skinloader.attr.DynamicAttr;

import java.util.List;

/**
 * Created by _SOLID
 * Date:2016/4/14
 * Time:10:26
 */
public interface IDynamicNewView {
    void dynamicAddView(View view, List<DynamicAttr> pDAttrs);
}
