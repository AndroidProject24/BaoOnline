package com.toan_itc.baoonline.injector;

import com.toan_it.library.library.injector.PerActivity;
import com.toan_it.library.library.injector.component.ApplicationComponent;
import com.toan_it.library.library.injector.module.ActivityModule;
import com.toan_itc.baoonline.ui.activity.MainActivity;
import com.toan_itc.baoonline.ui.fragment.HomeFragment;
import com.toan_itc.baoonline.ui.fragment.SkinFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  void inject(MainActivity mainActivity);

  void inject(HomeFragment homeFragment);

  void inject(SkinFragment skinFragment);
}