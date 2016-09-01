package com.toan_itc.baoonline.ui.readnews.di;

import com.toan_itc.baoonline.library.injector.component.ActivityComponent;
import com.toan_itc.baoonline.library.injector.component.ApplicationComponent;
import com.toan_itc.baoonline.library.injector.module.ActivityModule;
import com.toan_itc.baoonline.library.injector.scope.PerActivity;
import com.toan_itc.baoonline.ui.readnews.activity.ReadNewsActivity;

import dagger.Component;

/*
    *Created by Toan.IT
    *Date:09/08/2016
    *Email:huynhvantoan.itc@gmail.com
*/
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, ReadNewsModule.class})
public interface ReadNewsComponent extends ActivityComponent {

    void inject(ReadNewsActivity readNewsActivity);

}
