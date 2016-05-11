package com.toan_itc.baoonline.injector.module;

import android.app.Activity;

import com.toan_itc.baoonline.injector.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
  private final Activity mActivity;

  public ActivityModule(Activity activity) {
    mActivity = activity;
  }

  @Provides
  @ActivityScope
  public Activity activity() {
    return mActivity;
  }
}