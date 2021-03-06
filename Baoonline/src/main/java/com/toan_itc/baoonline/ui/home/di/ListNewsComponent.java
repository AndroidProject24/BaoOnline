package com.toan_itc.baoonline.ui.home.di;

import com.toan_itc.baoonline.injector.component.ApplicationComponent;
import com.toan_itc.baoonline.injector.module.FragmentModule;
import com.toan_itc.baoonline.injector.scope.PerFragment;
import com.toan_itc.baoonline.ui.home.fragment.ListNewsViewFragment;

import dagger.Component;

/**
 * Created by Toan.IT
 * Date: 09/08/2016
 * Email: huynhvantoan.itc@gmail.com
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = {FragmentModule.class, ListNewsModule.class})
public interface ListNewsComponent {

    void inject(ListNewsViewFragment listNewsFragment);

}