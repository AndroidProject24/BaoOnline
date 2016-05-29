package com.toan_it.library.library.injector.module;

import android.app.Activity;
import android.content.Context;

import com.toan_it.library.library.injector.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
  private final Activity mActivity;

  public ActivityModule(Activity activity) {
    mActivity = activity;
  }

  @Provides
  public Activity activity() {
    return mActivity;
  }

  @Provides
  @ActivityContext
  Context providesContext() {
    return mActivity;
  }
}