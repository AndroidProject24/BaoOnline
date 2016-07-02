package com.toan_it.library.library.activity;

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

import static dagger.internal.Preconditions.checkNotNull;


/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public abstract class BaseActivity extends SkinBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResourceID());
        changeStatusColor();
        setButterKnife();
        initbase();
        injectDependencies();
        initViews();
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
        RefWatcher refWatcher = BaseApplication.getRefWatcher();
        refWatcher.watch(this);
    }
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}