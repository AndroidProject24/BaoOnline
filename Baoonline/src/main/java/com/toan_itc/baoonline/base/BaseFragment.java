package com.toan_itc.baoonline.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;
import com.toan_itc.baoonline.BaseApplication;
import com.toan_itc.baoonline.base.presenter.BasePresenter;
import com.toan_itc.baoonline.base.view.BaseView;
import com.toan_itc.baoonline.injector.component.ApplicationComponent;
import com.toan_itc.baoonline.injector.module.FragmentModule;
import com.toan_itc.baoonline.injector.scope.HasComponent;
import com.toan_itc.data.libs.view.StateLayout;
import com.toan_itc.data.local.realm.RealmManager;
import com.toan_itc.data.utils.logger.Logger;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Toan.IT
 * Created by vantoan on 3/20/17.
 * Email: Huynhvantoan.itc@gmail.com
 */
public abstract class BaseFragment<T extends BasePresenter> extends SupportFragment implements BaseView {
  @Inject
  RealmManager mRealmManager;
  private Snackbar snackbar;
  private Context mContext;
  private Unbinder unbinder;
  private StateLayout mStateLayout = null;
  private boolean isInited = false;
  @Inject
  protected T mPresenter;
  protected String TAG = BaseFragment.class.getSimpleName();

  @Override
  public void onAttach(Context context) {
    mContext = context;
    super.onAttach(context);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
    injectDependencies();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    if (setLayoutResourceID() != 0) {
      return inflater.inflate(setLayoutResourceID(), container, false);
    } else {
      return super.onCreateView(inflater, container, savedInstanceState);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if(mPresenter!=null)
      mPresenter.attachView(this);
    unbinder = ButterKnife.bind(this, view);
    if (savedInstanceState == null) {
      if (!isHidden()) {
        isInited = true;
        if (null != getLoadingTargetView()) {
          mStateLayout = getLoadingTargetView();
        }
        initViews();
        initData();
      }
    }
  }

  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    if (!isInited && !hidden) {
      isInited = true;
      if (null != getLoadingTargetView()) {
        mStateLayout = getLoadingTargetView();
      }
      initViews();
      initData();
    }
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
    if(getActivity() instanceof BaseActivity)
      return ((BaseActivity) getActivity()).getApplicationComponent();
    if(getActivity() instanceof SimpleActivity)
      return ((SimpleActivity) getActivity()).getApplicationComponent();
    return null;
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
    if (mPresenter != null)
      mPresenter.detachView();
    Logger.d(TAG);
  }

  protected Snackbar snackBarBuild(String message) {
    if (getLoadingTargetView() == null) {
      throw new RuntimeException("This getLoadingTargetView not view!");
    }
    snackbar = Snackbar.make(getLoadingTargetView(), message, Snackbar.LENGTH_LONG);
    return snackbar;
  }

  protected void hideSnackBar() {
    if (snackbar != null) {
      snackbar.dismiss();
    }
    snackbar = null;
  }

  protected void toggleShowLoading(boolean toggle) {
    if(checkProgress()) {
      if (toggle)
        mStateLayout.showProgressView();
      else
        mStateLayout.showContentView();
    }
  }

  protected void toggleShowEmpty(String msg, View.OnClickListener onClickListener) {
    if(checkProgress()) {
      mStateLayout.showEmptyView(msg);
      if (onClickListener != null) {
        mStateLayout.setEmptyAction(onClickListener);
      }
    }
  }

  protected void toggleShowError(String msg, View.OnClickListener onClickListener) {
    if(checkProgress()) {
      mStateLayout.showErrorView(msg);
      if (onClickListener != null) {
        mStateLayout.setErrorAction(onClickListener);
      }
    }
  }

  private boolean checkProgress(){
    return mStateLayout!=null;
  }


  @Override
  public void showEmptyView(String message) {
    toggleShowEmpty(message, null);
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

  public int dimen(@DimenRes int resId) {
    return (int) getResources().getDimension(resId);
  }

  public int color(@ColorRes int resId) {
    return getResources().getColor(resId);
  }

  public int integer(@IntegerRes int resId) {
    return getResources().getInteger(resId);
  }

  public String string(@StringRes int resId) {
    return getResources().getString(resId);
  }
}
