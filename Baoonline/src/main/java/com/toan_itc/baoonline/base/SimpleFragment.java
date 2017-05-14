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
import com.toan_itc.data.utils.logger.Logger;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Toan.IT
 * Created by vantoan on 3/20/17.
 * Email: Huynhvantoan.itc@gmail.com
 */

public abstract class SimpleFragment extends SupportFragment {
  private Snackbar snackbar;
  private Context mContext;
  private Unbinder unbinder;
  private boolean isInited = false;
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

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);
    if (savedInstanceState == null) {
      if (!isHidden()) {
        isInited = true;
        initViews();
        initData();
      }
    } else {
      /*if (!isSupportHidden()) {
        isInited = true;
        initViews();
        initData();
      }*/
    }
  }
  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    if (!isInited && !hidden) {
      isInited = true;
      initViews();
      initData();
    }
  }

  protected abstract @LayoutRes int setLayoutResourceID();

  protected abstract void initViews();

  protected abstract void initData();

  public Context getContext() {
    return mContext;
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

  protected Snackbar snackBarBuild(View view,String message) {
    if (view == null) {
      throw new RuntimeException("This getLoadingTargetView not view!");
    }
    snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
    return snackbar;
  }

  protected void hideSnackBar() {
    if (snackbar != null) {
      snackbar.dismiss();
    }
    snackbar = null;
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
