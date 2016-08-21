package com.toan_itc.baoonline.library.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.squareup.leakcanary.RefWatcher;
import com.toan_it.library.skinloader.base.SkinBaseActivity;
import com.toan_itc.baoonline.library.BaseApplication;
import com.toan_itc.baoonline.library.base.view.EmptyView;
import com.toan_itc.baoonline.library.base.view.ErrorView;
import com.toan_itc.baoonline.library.base.view.LoadView;
import com.toan_itc.baoonline.library.injector.component.ApplicationComponent;
import com.toan_itc.baoonline.library.injector.module.ActivityModule;
import com.toan_itc.data.libs.view.VaryViewHelperController;
import com.toan_itc.data.utils.logger.Logger;

import butterknife.ButterKnife;

import static dagger.internal.Preconditions.checkNotNull;


/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public abstract class BaseActivity extends SkinBaseActivity implements LoadView,ErrorView,EmptyView {
    /*@Inject
    Navigator1 navigator;*/
    private VaryViewHelperController mVaryViewHelperController = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResourceID());
        getApplicationComponent().inject(this);
        changeStatusColor();
        initViews();
        initData();
    }
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        if (null != getLoadingTargetView()) {
            mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        }
    }
    protected abstract void initViews();

    protected abstract @LayoutRes int setLayoutResourceID();

    protected abstract void initData();

    protected abstract void injectDependencies();

    protected abstract View getLoadingTargetView();

    protected void toggleShowLoading(boolean toggle) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (toggle) {
            mVaryViewHelperController.showLoading();
        } else {
            mVaryViewHelperController.restore();
        }
    }

    protected void toggleShowEmpty(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for Empty");
        }

        if (toggle) {
            mVaryViewHelperController.showEmpty(msg, onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    protected void toggleShowError(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for Error");
        }

        if (toggle) {
            mVaryViewHelperController.showError(msg, onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    protected void toggleNetworkError(boolean toggle, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for NetworkError");
        }

        if (toggle) {
            mVaryViewHelperController.showNetworkError(onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    @Override
    public void showEmptyView(String message) {
        toggleShowEmpty(true,message,null);
    }

    @Override
    public void showError(String message) {
        toggleShowError(true, message, null);
    }

    @Override
    public void showNetworkError() {
        toggleNetworkError(true, null);
    }

    @Override
    public void showLoading() {
        toggleShowLoading(true);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false);
    }

    protected void addFagment(int containerViewId, Fragment fragment){
        checkNotNull(fragment);
        FragmentTransaction transaction =  this.getSupportFragmentManager().beginTransaction();
        transaction.add(containerViewId, fragment,fragment.getClass().getName());
        transaction.commit();
    }

    protected void replaceFagment(int containerViewId, Fragment fragment){
        checkNotNull(fragment);
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(containerViewId, fragment,fragment.getClass().getName());
        transaction.commit();
    }

    /*protected Navigator1 getNavigator(){
        return navigator;
    }*/

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
