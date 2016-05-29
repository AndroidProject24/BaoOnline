package com.toan_it.library.library.mvp.presenter;

import com.toan_it.library.library.mvp.view.BaseView;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<T extends BaseView> implements Presenter<T> {
    private T mMvpView;
    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }
    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(BaseView) before" + "requesting data to the Presenter");
        }
    }
}

