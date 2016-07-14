package com.toan_itc.baoonline.library.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.squareup.leakcanary.RefWatcher;
import com.toan_it.library.skinloader.base.SkinBaseActivity;
import com.toan_itc.baoonline.library.BaseApplication;
import com.toan_itc.baoonline.library.injector.component.ActivityComponent;
import com.toan_itc.baoonline.library.injector.component.ApplicationComponent;
import com.toan_itc.baoonline.library.injector.module.ActivityModule;
import com.toan_itc.baoonline.library.injector.scope.HasComponent;
import com.toan_itc.baoonline.navigation.Navigator;
import com.toan_itc.data.utils.logger.Logger;

import javax.inject.Inject;

import butterknife.ButterKnife;

import static dagger.internal.Preconditions.checkNotNull;


/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public abstract class BaseActivity <C extends ActivityComponent> extends SkinBaseActivity implements HasComponent<C> {
    @Inject
    Navigator navigator;
    private C component;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResourceID());
        ButterKnife.bind(this);
        this.getApplicationComponent().inject(this);
        initializerInjector();
        changeStatusColor();
        initViews();
        initData();
    }

    protected abstract void initViews();

    protected abstract @LayoutRes int setLayoutResourceID();

    protected abstract void initData();

    protected abstract C injectDependencies();

    protected void addFagment(int containerViewId, Fragment fragment){
        checkNotNull(fragment);
        FragmentTransaction transaction =  this.getSupportFragmentManager().beginTransaction();
        transaction.add(containerViewId, fragment);
        transaction.commit();
    }

    protected void replaceFagment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId){
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment,fragment.getClass().getName());
        transaction.commit();
    }
    protected void initializerInjector() {
        this.component = injectDependencies();
    }

    protected Navigator getNavigator(){
        return navigator;
    }
    @Override
    public C getComponent() {
        return component;
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((BaseApplication)getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.d("BaseActivity:onDestroy");
        RefWatcher refWatcher = BaseApplication.getRefWatcher();
        refWatcher.watch(this);
    }
}
