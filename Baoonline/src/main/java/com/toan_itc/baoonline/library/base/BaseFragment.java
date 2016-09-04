package com.toan_itc.baoonline.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;
import com.toan_itc.baoonline.library.BaseApplication;
import com.toan_itc.baoonline.library.base.view.EmptyView;
import com.toan_itc.baoonline.library.base.view.ErrorView;
import com.toan_itc.baoonline.library.base.view.LoadView;
import com.toan_itc.baoonline.library.injector.component.ApplicationComponent;
import com.toan_itc.baoonline.library.injector.module.FragmentModule;
import com.toan_itc.baoonline.library.injector.scope.HasComponent;
import com.toan_itc.data.libs.view.StateLayout;
import com.toan_itc.data.local.realm.RealmManager;
import com.toan_itc.data.utils.logger.Logger;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Toan.IT
 * Date:22/5/2016
 */
public abstract class BaseFragment extends Fragment implements LoadView,ErrorView,EmptyView {
    @Inject
    RealmManager mRealmManager;
    private Snackbar snackbar;
    private Context mContext;
    private Unbinder unbinder;
    private StateLayout mStateLayout = null;
    protected String TAG = BaseFragment.class.getSimpleName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        getApplicationComponent().inject(this);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (setLayoutResourceID() != 0) {
            return inflater.inflate(setLayoutResourceID(), container,false);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        mContext = getContext();
        if (null != getLoadingTargetView()) {
            mStateLayout = getLoadingTargetView();
        }
        injectDependencies();
        initViews();
        initData();
    }

    protected abstract @LayoutRes int setLayoutResourceID();
    protected abstract void injectDependencies();
    protected abstract void initViews();
    protected abstract void initData();
    protected abstract StateLayout getLoadingTargetView();
    public Context getContext() {
        return mContext;
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((BaseActivity)getActivity()).getApplicationComponent();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.d(TAG);
        unbinder.unbind();
        RefWatcher refWatcher = BaseApplication.getRefWatcher();
        refWatcher.watch(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d(TAG);
    }

    protected Snackbar snackBarBuild(String message){
        if(getLoadingTargetView()==null){
            throw new RuntimeException("This getLoadingTargetView not view!");
        }
        snackbar = Snackbar.make(getLoadingTargetView(), message, Snackbar.LENGTH_LONG);
        return snackbar;
    }

    protected void hideSnackBar(){
        if (snackbar != null){
            snackbar.dismiss();
        }
        snackbar = null;
    }

    protected void toggleShowLoading(boolean toggle) {
        if (null == mStateLayout) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (toggle)
            mStateLayout.showProgressView();
        else
            mStateLayout.showContentView();
    }

    protected void toggleShowEmpty(String msg, View.OnClickListener onClickListener) {
        if (null == mStateLayout) {
            throw new IllegalArgumentException("You must return a right target view for Empty");
        }
        mStateLayout.showEmptyView(msg);
        if(onClickListener!=null){
            mStateLayout.setEmptyAction(onClickListener);
        }
    }

    protected void toggleShowError(String msg, View.OnClickListener onClickListener) {
        if (null == mStateLayout) {
            throw new IllegalArgumentException("You must return a right target view for Error");
        }
        mStateLayout.showErrorView(msg);
        if(onClickListener!=null){
            mStateLayout.setErrorAction(onClickListener);
        }
    }

    public RealmManager getRealmManager(){
        return mRealmManager;
    }

    @Override
    public void showEmptyView(String message) {
        toggleShowEmpty(message,null);
    }

    @Override
    public void showError(String message) {
        toggleShowError(message, null);
    }

    @Override
    public void showLoading() {
        toggleShowLoading(true);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false);
    }
}
