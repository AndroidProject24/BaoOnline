package com.toan_itc.baoonline.injector.component;

import com.toan_itc.baoonline.injector.ActivityScope;
import com.toan_itc.baoonline.ui.activity.MainActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent extends ApplicationComponent {

  void inject(MainActivity mainActivity);

  //void inject(MainActivity mainActivity);

}