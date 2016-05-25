package com.toan_itc.baoonline.injector.component;

import com.toan_itc.baoonline.injector.PerActivity;
import com.toan_itc.baoonline.ui.activity.MainActivity;
import com.toan_itc.baoonline.ui.fragment.HomeFragment;
import com.toan_itc.baoonline.ui.fragment.SkinFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent extends ApplicationComponent {

  void inject(MainActivity mainActivity);

  void inject(HomeFragment homeFragment);

  void inject(SkinFragment skinFragment);
}