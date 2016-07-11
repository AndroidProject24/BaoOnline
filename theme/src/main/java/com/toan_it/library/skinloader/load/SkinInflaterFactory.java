package com.toan_it.library.skinloader.load;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.toan_it.library.skinloader.attr.AttrFactory;
import com.toan_it.library.skinloader.attr.DynamicAttr;
import com.toan_it.library.skinloader.attr.SkinAttr;
import com.toan_it.library.skinloader.config.SkinConfig;
import com.toan_it.library.skinloader.entity.SkinItem;
import com.toan_it.library.skinloader.util.L;
import com.toan_it.library.skinloader.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toan.IT
 * Date: 22/05/2016
 */
public class SkinInflaterFactory implements LayoutInflaterFactory {

    private static String TAG = "SkinInflaterFactory";
    private List<SkinItem> mSkinItems = new ArrayList<SkinItem>();


    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

        boolean isSkinEnable = attrs.getAttributeBooleanValue(SkinConfig.NAMESPACE, SkinConfig.ATTR_SKIN_ENABLE, false);
        if (!isSkinEnable) {
            return null;//返回空就使用默认的InflaterFactory
        }
        View view = createView(context, name, attrs);
        if (view == null) {//没有找到这个View
            return null;
        }
        parseSkinAttr(context, attrs, view);
        return view;
    }
    private View createView(Context context, String name, AttributeSet attrs) {
        Log.i(TAG, "createView:" + name);
        View view = null;
        try {
            if (-1 == name.indexOf('.')) {
                if ("View".equals(name)) {
                    view = LayoutInflater.from(context).createView(name, "android.view.", attrs);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.widget.", attrs);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.webkit.", attrs);
                }
            } else {
                view = LayoutInflater.from(context).createView(name, null, attrs);
            }

            L.i(TAG, "about to create " + name);

        } catch (Exception e) {
            L.e(TAG, "error while create 【" + name + "】 : " + e.getMessage());
            view = null;
        }
        return view;
    }

    private void parseSkinAttr(Context context, AttributeSet attrs, View view) {
        List<SkinAttr> viewAttrs = new ArrayList<SkinAttr>();//存储View可更换皮肤属性的集合
        for (int i = 0; i < attrs.getAttributeCount(); i++) {//遍历当前View的属性
            String attrName = attrs.getAttributeName(i);//属性名
            String attrValue = attrs.getAttributeValue(i);//属性值
            if (!AttrFactory.isSupportedAttr(attrName)) {
                continue;
            }
            if (attrValue.startsWith("@")) {//也就是引用类型，形如@color/red
                try {
                    int id = Integer.parseInt(attrValue.substring(1));//资源的id
                    String entryName = context.getResources().getResourceEntryName(id);//入口名，例如text_color_selector
                    String typeName = context.getResources().getResourceTypeName(id);//类型名，例如color、background
                    SkinAttr mSkinAttr = AttrFactory.get(attrName, id, entryName, typeName);
                    L.i("parseSkinAttr", "view:" + view.getClass().getSimpleName());
                    L.i("parseSkinAttr", "attrName:" + attrName + " | attrValue:" + attrValue);
                    L.i("parseSkinAttr", "id:" + id);
                    L.i("parseSkinAttr", "entryName:" + entryName);
                    L.i("parseSkinAttr", "typeName:" + typeName);
                    if (mSkinAttr != null) {
                        viewAttrs.add(mSkinAttr);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!ListUtils.isEmpty(viewAttrs)) {
            SkinItem skinItem = new SkinItem();
            skinItem.view = view;
            skinItem.attrs = viewAttrs;
            mSkinItems.add(skinItem);
            if (SkinManager.getInstance().isExternalSkin()) {//如果当前皮肤来自于外部
                skinItem.apply();
            }
        }
    }

    public void applySkin() {
        if (ListUtils.isEmpty(mSkinItems)) {
            return;
        }

        for (SkinItem si : mSkinItems) {
            if (si.view == null) {
                continue;
            }
            si.apply();
        }
    }

    public void clean() {
        if (ListUtils.isEmpty(mSkinItems)) {
            return;
        }
        for (SkinItem si : mSkinItems) {
            if (si.view == null) {
                continue;
            }
            si.clean();
        }
    }

    public void addSkinView(SkinItem item) {
        mSkinItems.add(item);
    }

    public void dynamicAddSkinEnableView(Context context, View view, String attrName, int attrValueResId) {
        int id = attrValueResId;
        String entryName = context.getResources().getResourceEntryName(id);
        String typeName = context.getResources().getResourceTypeName(id);
        SkinAttr mSkinAttr = AttrFactory.get(attrName, id, entryName, typeName);
        SkinItem skinItem = new SkinItem();
        skinItem.view = view;
        List<SkinAttr> viewAttrs = new ArrayList<SkinAttr>();
        viewAttrs.add(mSkinAttr);
        skinItem.attrs = viewAttrs;
        skinItem.apply();
        addSkinView(skinItem);
    }

    public void dynamicAddSkinEnableView(Context context, View view, List<DynamicAttr> pDAttrs) {
        List<SkinAttr> viewAttrs = new ArrayList<SkinAttr>();
        SkinItem skinItem = new SkinItem();
        skinItem.view = view;

        for (DynamicAttr dAttr : pDAttrs) {
            int id = dAttr.refResId;
            String entryName = context.getResources().getResourceEntryName(id);
            String typeName = context.getResources().getResourceTypeName(id);
            SkinAttr mSkinAttr = AttrFactory.get(dAttr.attrName, id, entryName, typeName);
            viewAttrs.add(mSkinAttr);
        }

        skinItem.attrs = viewAttrs;
        skinItem.apply();
        addSkinView(skinItem);
    }
}
