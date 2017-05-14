package com.toan_itc.baoonline.base.presenter;

import com.toan_itc.baoonline.base.view.BaseView;

/**
 * Every presenter in the app must either implement this interfaceListener or extend Presenter
 * indicating the BaseView type that wants to be attached with.
 */
/**
 * Toan.IT
 * Created by vantoan on 3/20/17.
 * Email: Huynhvantoan.itc@gmail.com
 */
public interface Presenter<V extends BaseView> {

	void attachView(V mvpView);

	void detachView();
}