package com.toan_itc.baoonline.injector;

import com.toan_itc.baoonline.library.injector.module.ActivityModule;
import com.toan_itc.baoonline.library.injector.module.FragmentModule;
import com.toan_itc.baoonline.library.injector.scope.ActivityScope;
import com.toan_itc.baoonline.ui.activity.MainActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    
  void inject(MainActivity mainActivity);

  FragmentComponent plus(FragmentModule module);
}