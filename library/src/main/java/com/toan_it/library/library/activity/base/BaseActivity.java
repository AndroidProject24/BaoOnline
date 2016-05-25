package com.toan_it.library.library.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.squareup.leakcanary.RefWatcher;
import com.toan_it.library.library.BaseApplication;
import com.toan_it.library.skinloader.base.SkinBaseActivity;

import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public abstract class BaseActivity extends SkinBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        injector();
        setContentView(setLayoutResourceID());
        setButterKnife();
        setUpView();
        setUpData();
    }
    private void setButterKnife() {
        ButterKnife.bind(this);
    }
    protected void init() {

    }

    protected abstract void setUpView();

    protected abstract int setLayoutResourceID();

    protected abstract void setUpData();

    protected abstract void injector();

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
    /*protected void remove_fragment(){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(Home.class.getName());
        if(fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
        Fragment menufragment = getSupportFragmentManager().findFragmentByTag(fragment.class.getName());
        if(menufragment != null) {
            getSupportFragmentManager().beginTransaction().remove(menufragment).commit();
        }
        Fragment fragmentseach = getSupportFragmentManager().findFragmentByTag(PlayerSearchFragment.class.getName());
        if(fragmentseach != null) {
            getSupportFragmentManager().beginTransaction().remove(fragmentseach).commit();
        }
    }*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = BaseApplication.getRefWatcher();
        refWatcher.watch(this);
    }
}
