package com.toan_itc.baoonline.ui.splash.activity;

import android.os.Bundle;
import android.view.View;

import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.base.BaseActivity;

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
    protected void initViews() {

    }
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getNavigator().navigateToMainActivity(SplashScreen.this);
            }
        },2000);*/
    }

    @Override
    protected void injectDependencies() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
/*
    @Override
    protected RssActivityComponent injectDependencies() {
        return null;
    }*/

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
    }
}