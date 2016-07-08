package com.toan_itc.baoonline.injector;

import com.toan_itc.baoonline.library.injector.module.FragmentModule;
import com.toan_itc.baoonline.library.injector.scope.FragmentScope;
import com.toan_itc.baoonline.ui.fragment.HomeFragment;
import com.toan_itc.baoonline.ui.fragment.SkinFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(HomeFragment homeFragment);

    void inject(SkinFragment skinFragment);
}
