package com.toan_it.library.library.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.squareup.leakcanary.RefWatcher;
import com.toan_it.library.library.BaseApplication;
import com.toan_it.library.library.utils.Logger;
import com.toan_it.library.skinloader.base.SkinBaseActivity;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public abstract class BaseActivity extends SkinBaseActivity {
    private CompositeSubscription mCompositeSubscription;
    private Subscription subscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResourceID());
        changeStatusColor();
        setButterKnife();
        initbase();
        injectDependencies();
        injectViews();
        injectData();
    }
    private void setButterKnife() {
        ButterKnife.bind(this);
    }
    private void initbase() {
        Logger.initTag(TAG,true);
    }

    protected String TAG = getTAG();

    protected abstract String getTAG();

    protected abstract void injectViews();

    protected abstract int setLayoutResourceID();

    protected abstract void injectData();

    protected abstract void injectDependencies();

    protected void startActivityWithoutExtras(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void startActivityWithExtras(Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivity(intent);

    }
    protected void addFagment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId){
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }
    protected void replaceFagment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId){
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }
    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }

    public void addSubscription(Subscription s) {
        if (s == null) {
            return;
        }
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        this.mCompositeSubscription.add(s);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Logger.d(TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.d(TAG);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.d(TAG);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.d(TAG);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.d(TAG);
        if (subscription != null) {
            subscription.unsubscribe();
        }
        if (this.mCompositeSubscription != null) {

            this.mCompositeSubscription.unsubscribe();
        }
        RefWatcher refWatcher = BaseApplication.getRefWatcher();
        refWatcher.watch(this);
    }
    @Override
    public String toString() {
        return getClass().getName();
    }
}
