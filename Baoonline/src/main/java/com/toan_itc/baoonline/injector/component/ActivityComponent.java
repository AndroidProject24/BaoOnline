package com.toan_itc.baoonline.injector.component;

import com.toan_itc.baoonline.injector.module.ActivityModule;
import com.toan_itc.baoonline.injector.scope.PerActivity;
import com.toan_itc.baoonline.ui.home.activity.MainActivity;
import com.toan_itc.baoonline.ui.splash.activity.SplashScreen;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  void inject(SplashScreen splashScreen);

  void inject(MainActivity mainActivity);
}
