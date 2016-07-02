package com.toan_it.library.library.mvp.presenter;

import android.support.annotation.NonNull;

import com.toan_it.library.library.mvp.view.BaseView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<T extends BaseView> implements Presenter<T> {
    private T mMvpView;
    @NonNull
    private CompositeSubscription subscriptionsToUnsubscribeOnUnbindView = new CompositeSubscription();
    @Override
    public void attachView(T mvpView) {
        this.mMvpView = mvpView;
    }
    @Override
    public void detachView() {
        this.mMvpView = null;
        // Unsubscribe all subscriptions that need to be unsubscribed in this lifecycle state.
        this.subscriptionsToUnsubscribeOnUnbindView.clear();
    }

    private boolean isViewAttached() {
        return this.mMvpView != null;
    }

    public T getMvpView() {
        if(isViewAttached())
            return this.mMvpView;
        else
            throw new IllegalStateException("Unexpected view!" + this.mMvpView);
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    private static class MvpViewNotAttachedException extends RuntimeException {
        private MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(BaseView) before" + "requesting data to the Presenter");
        }
    }
    //Clear
    protected final void unsubscribeOnUnbindView(@NonNull Subscription subscription, @NonNull Subscription... subscriptions) {
        this.subscriptionsToUnsubscribeOnUnbindView.add(subscription);

        for (Subscription s : subscriptions) {
            this.subscriptionsToUnsubscribeOnUnbindView.add(s);
        }
    }
}

