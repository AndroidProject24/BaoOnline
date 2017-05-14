package com.toan_itc.baoonline.base.presenter;

import android.support.annotation.NonNull;

import com.toan_itc.baoonline.base.view.BaseView;
import com.toan_itc.data.rxjava.RxBus;
import com.toan_itc.data.utils.logger.Logger;

import java.lang.ref.WeakReference;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Toan.IT
 * Created by vantoan on 3/20/17.
 * Email: Huynhvantoan.itc@gmail.com
 */
public class RxPresenter<T extends BaseView> implements Presenter<T> {

  private WeakReference<T> mMvpView;
  private CompositeSubscription mCompositeSubscription;

  public T getView() {
    if (isViewAttached())
      return this.mMvpView.get();
    else
      Logger.wtf("", "This presenter does not set view!");
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

  @Override
  public void attachView(@NonNull T view) {
    this.mMvpView = new WeakReference<>(view);
  }

  @Override
  public void detachView() {
    this.mMvpView = null;
    unSubscribe();
  }
  private void unSubscribe() {
    if (mCompositeSubscription != null) {
      mCompositeSubscription.unsubscribe();
    }
  }

  protected void addSubscrebe(Subscription subscription) {
    if (mCompositeSubscription == null) {
      mCompositeSubscription = new CompositeSubscription();
    }
    mCompositeSubscription.add(subscription);
  }

  protected <U> void addRxBusSubscribe(Class<U> eventType, Action1<U> act) {
    if (mCompositeSubscription == null) {
      mCompositeSubscription = new CompositeSubscription();
    }
    mCompositeSubscription.add(RxBus.getDefault().toDefaultObservable(eventType, act));
  }

}
