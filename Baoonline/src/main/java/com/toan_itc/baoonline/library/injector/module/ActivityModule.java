package com.toan_itc.baoonline.library.injector.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

  private AppCompatActivity activity;

  public ActivityModule(AppCompatActivity activity) {
    this.activity = activity;
  }

  @Provides
  public Activity activity() {
    return activity;
  }

  @Provides
  public Context context() {
    return activity;
  }

  @Provides
  LayoutInflater layoutInflater() {
    return activity.getLayoutInflater();
  }
}