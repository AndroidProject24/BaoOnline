package com.toan_itc.baoonline.library.base.view;

import android.view.View;

/**
 * Created by policante on 1/19/16.
 */
public interface ErrorView {

    void showError(String message, View.OnClickListener onClickListener);

    void hideError();
}
