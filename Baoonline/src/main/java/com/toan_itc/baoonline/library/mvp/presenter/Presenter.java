package com.toan_itc.baoonline.library.mvp.presenter;

import com.toan_itc.baoonline.library.mvp.view.BaseView;

/**
 * Every presenter in the app must either implement this interfaceListener or extend Presenter
 * indicating the BaseView type that wants to be attached with.
 */
public interface Presenter<V extends BaseView> {

    void attachView(V mvpView);

    void detachView();
}