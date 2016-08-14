package com.toan_itc.baoonline.library.injector.component;

import com.toan_itc.baoonline.library.injector.module.ActivityModule;
import com.toan_itc.baoonline.library.injector.scope.PerActivity;
import com.toan_itc.baoonline.ui.details.activity.DetailsActivity;
import com.toan_itc.baoonline.ui.home.activity.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(DetailsActivity detailsActivity);
}
