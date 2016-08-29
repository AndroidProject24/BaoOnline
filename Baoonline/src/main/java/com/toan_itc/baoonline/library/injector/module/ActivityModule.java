package com.toan_itc.baoonline.library.injector.module;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.toan_itc.baoonline.library.injector.qualifier.ActivityContext;
import com.toan_itc.baoonline.library.injector.scope.PerActivity;
import com.toan_itc.baoonline.navigation.ActivityNavigator;
import com.toan_itc.baoonline.navigation.Navigator;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

  private final FragmentActivity mActivity;

  public ActivityModule(FragmentActivity activity) {
    mActivity = activity;
  }

  @Provides
  @PerActivity
  @ActivityContext
  Context provideActivityContext() { return mActivity; }

  @Provides
  @PerActivity
  FragmentManager provideFragmentManager() { return mActivity.getSupportFragmentManager(); }

  @Provides
  @PerActivity
  Navigator provideNavigator() { return new ActivityNavigator(mActivity); }

}