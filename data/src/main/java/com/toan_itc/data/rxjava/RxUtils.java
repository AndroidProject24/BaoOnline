package com.toan_itc.data.rxjava;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class RxUtils {
    private CompositeSubscription mCompositeSubscription;

    public void addCompositeSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    public void clearSubscription(Subscription subscription) {
        if (subscription != null) {
            subscription.unsubscribe();
            subscription=null;
        }
    }

    public void clearCompositeSubscription(){
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.clear();
            this.mCompositeSubscription=null;
        }
    }

}
