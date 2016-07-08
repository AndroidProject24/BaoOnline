package com.toan_itc.baoonline.library.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.squareup.leakcanary.RefWatcher;
import com.toan_it.library.skinloader.base.SkinBaseActivity;
import com.toan_itc.baoonline.injector.ActivityComponent;
import com.toan_itc.baoonline.library.BaseApplication;
import com.toan_itc.baoonline.library.injector.module.ActivityModule;
import com.toan_itc.baoonline.library.utils.Logger;

import butterknife.ButterKnife;

import static dagger.internal.Preconditions.checkNotNull;


/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public abstract class BaseActivity extends SkinBaseActivity {
    private ActivityComponent mActivityComponent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResourceID());
        changeStatusColor();
        initbase();
        injectDependencies();
        initViews();
        setButterKnife();
        initData();
    }
    private void setButterKnife() {
        ButterKnife.bind(this);
    }
    private void initbase() {
        Logger.initTag(TAG);
    }

    protected String TAG = getTAG();

    protected abstract String getTAG();

    protected abstract void initViews();

    protected abstract int setLayoutResourceID();

    protected abstract void initData();

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
        transaction.add(frameId, fragment,fragment.getClass().getName());
        transaction.commit();
    }
    protected void replaceFagment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId){
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment,fragment.getClass().getName());
        transaction.commit();
    }
    @NonNull
    public ActivityComponent getComponent() {
        if (mActivityComponent == null) {
            BaseApplication baseApplication = (BaseApplication) getApplication();
            mActivityComponent = baseApplication.getApplicationComponent().plus(new ActivityModule(this));
        }
        return mActivityComponent;
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
        RefWatcher refWatcher = BaseApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
