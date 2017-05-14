package com.toan_itc.baoonline.base.presenter;

import android.support.annotation.NonNull;

import com.toan_itc.baoonline.base.view.BaseView;
import com.toan_itc.data.utils.logger.Logger;

import java.lang.ref.WeakReference;
/**
 * Toan.IT
 * Created by vantoan on 3/20/17.
 * Email: Huynhvantoan.itc@gmail.com
 */
public class BasePresenter<T extends BaseView> implements Presenter<T> {
	private WeakReference<T> mMvpView;
	private static final String TAG = BasePresenter.class.getSimpleName();

	@Override
	public void attachView(@NonNull T mvpView) {
		this.mMvpView = new WeakReference<>(mvpView);
	}

	@Override
	public void detachView() {
		this.mMvpView = null;
	}

	public T getView() {
		if (isViewAttached())
			return this.mMvpView.get();
		else
			Logger.wtf(TAG, "This presenter does not set view!");
		throw new RuntimeException("This presenter does not set view!");
	}

	private boolean isViewAttached() {
		return this.mMvpView != null && this.mMvpView.get() != null;
	}

	public void checkViewAttached() {
		if (!isViewAttached()) throw new MvpViewNotAttachedException();
	}

	private static class MvpViewNotAttachedException extends RuntimeException {
		private MvpViewNotAttachedException() {
			super("Please call Presenter.attachView(BaseView) before" + "requesting data to the Presenter");
		}
	}
}

