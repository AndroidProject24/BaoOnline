package com.toan_itc.baoonline.injector.module;

import android.app.Activity;

import com.toan_itc.baoonline.injector.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
  private final Activity mActivity;

  public ActivityModule(Activity activity) {
    mActivity = activity;
  }

  @Provides
  @ActivityContext
  public Activity activity() {
    return mActivity;
  }
}