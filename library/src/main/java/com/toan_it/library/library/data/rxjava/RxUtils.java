package com.toan_it.library.library.data.rxjava;

import rx.Subscription;

public class RxUtils {

    public static void unsubscribeIfNotNull(Subscription subscription) {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}