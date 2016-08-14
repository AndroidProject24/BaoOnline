package com.toan_itc.baoonline.library.base.presenter;

import android.support.annotation.NonNull;

import com.toan_itc.baoonline.library.base.view.BaseView;
import com.toan_itc.data.utils.logger.Logger;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Base class that implements the Presenter interfaceListener and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<T extends BaseView> implements Presenter<T> {
    private T mMvpView;
    private static final String TAG = BasePresenter.class.getSimpleName();

    @NonNull
    private CompositeSubscription subscriptionsToUnsubscribeOnUnbindView = new CompositeSubscription();

    @Override
    public void attachView(@NonNull T mvpView) {
        this.mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        this.mMvpView = null;
        // Unsubscribe all subscriptions that need to be unsubscribed in this lifecycle state.
        this.subscriptionsToUnsubscribeOnUnbindView.clear();
    }

    public T getView() {
        if(this.mMvpView == null) {
            Logger.wtf(TAG, "This presenter does not set view!");
            throw new RuntimeException("This presenter does not set view!");
        }return this.mMvpView;
    }

    private boolean isViewAttached() {
        return this.mMvpView != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    private static class MvpViewNotAttachedException extends RuntimeException {
        private MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(BaseView) before" + "requesting data to the Presenter");
        }
    }

    protected final void addSubscribe(@NonNull Subscription subscription, @NonNull Subscription... subscriptions) {
        this.subscriptionsToUnsubscribeOnUnbindView.add(subscription);

        for (Subscription s : subscriptions) {
            this.subscriptionsToUnsubscribeOnUnbindView.add(s);
        }
    }
}

