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

    public void unCompositeSubscription(){
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.clear();
            this.mCompositeSubscription=null;
        }
    }

    public void unSubscribe(Subscription subscription) {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    public static void unSubscribe(Subscription... subscriptions)
    {
        for (Subscription subscription : subscriptions)
        {
            if (subscription != null && !subscription.isUnsubscribed())
            {
                subscription.unsubscribe();
            }
        }
    }


}
