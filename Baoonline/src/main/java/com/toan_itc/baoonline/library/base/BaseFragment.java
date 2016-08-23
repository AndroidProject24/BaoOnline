package com.toan_itc.baoonline.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;
import com.toan_it.library.skinloader.base.SkinBaseFragment;
import com.toan_itc.baoonline.library.BaseApplication;
import com.toan_itc.baoonline.library.base.view.EmptyView;
import com.toan_itc.baoonline.library.base.view.ErrorView;
import com.toan_itc.baoonline.library.base.view.LoadView;
import com.toan_itc.baoonline.library.injector.component.ApplicationComponent;
import com.toan_itc.baoonline.library.injector.module.FragmentModule;
import com.toan_itc.baoonline.library.injector.scope.HasComponent;
import com.toan_itc.data.libs.view.VaryViewHelperController;
import com.toan_itc.data.utils.logger.Logger;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by Toan.IT
 * Date:22/5/2016
 */
public abstract class BaseFragment extends SkinBaseFragment implements LoadView,ErrorView,EmptyView {
    private Snackbar snackbar;
    private Context mContext;
    private Unbinder unbinder;
    private VaryViewHelperController mVaryViewHelperController = null;
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
            mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        }
        injectDependencies();
        initViews();
        initData();
    }

    protected abstract @LayoutRes int setLayoutResourceID();
    protected abstract void injectDependencies();
    protected abstract void initViews();
    protected abstract void initData();
    protected abstract View getLoadingTargetView();
    public Context getContext() {
        return mContext;
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

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.d(TAG);
    }

    protected Snackbar snackbarBuild(String message){
        if(getLoadingTargetView()==null){
            throw new RuntimeException("This getLoadingTargetView not view!");
        }
        snackbar = Snackbar.make(getLoadingTargetView(), message, Snackbar.LENGTH_LONG);
        return snackbar;
    }

    protected void hideSnackbar(){
        if (snackbar != null){
            snackbar.dismiss();
        }
        snackbar = null;
    }
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
}
