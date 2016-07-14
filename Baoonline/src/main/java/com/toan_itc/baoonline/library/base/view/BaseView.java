package com.toan_itc.baoonline.library.base.view;

import android.content.Context;

/**
 * Base interfaceListener that any class that wants to act as a BaseView in the MVP (Data BaseView Presenter)
 * pattern must implement. Generally this interfaceListener will be extended by a more specific interfaceListener
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface BaseView {
    Context getContext();
}