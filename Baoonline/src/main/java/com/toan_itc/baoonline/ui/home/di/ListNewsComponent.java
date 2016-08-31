package com.toan_itc.baoonline.ui.home.di;

import com.toan_itc.baoonline.library.injector.component.ApplicationComponent;
import com.toan_itc.baoonline.library.injector.module.FragmentModule;
import com.toan_itc.baoonline.library.injector.scope.PerFragment;
import com.toan_itc.baoonline.ui.home.fragment.ListNewsFragment;

import dagger.Component;

/**
 * Created by Toan.IT
 * Date: 09/08/2016
 * Email: huynhvantoan.itc@gmail.com
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = {FragmentModule.class, ListNewsModule.class})
public interface ListNewsComponent {

    void inject(ListNewsFragment listNewsFragment);

}