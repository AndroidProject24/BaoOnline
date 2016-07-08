package com.toan_itc.baoonline.ui.activity;

import android.os.Bundle;

import com.toan_itc.baoonline.library.activity.BaseActivity;

import rx.Subscription;

/**
 * Created by Toan.IT
 * Date: 22/05/2016
 */
public class SplashScreen extends BaseActivity {
    Subscription subscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void initViews() {

    }
    @Override
    protected int setLayoutResourceID() {
        return 0;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void injectDependencies() {

    }

    @Override
    protected void onStart() {
        super.onStart();
       /* subscription = PlayStore.checkForUpdates(this, "Google-Play-Android-Developer.json", PlayStore.ReleaseType.BETA)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    String msg;
                    if (s == null) {
                        msg = "No updates.";
                    } else {
                        msg = s.toString();
                    }
                    new AlertDialog.Builder(this)
                            .setMessage(msg)
                            .setPositiveButton(android.R.string.ok, null)
                            .show();
                }, Throwable::printStackTrace);*/
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
