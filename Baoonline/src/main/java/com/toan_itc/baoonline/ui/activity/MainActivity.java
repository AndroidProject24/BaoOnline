package com.toan_itc.baoonline.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.toan_it.library.skinloader.base.SkinBaseFragment;
import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.base.BaseToolbar;
import com.toan_itc.baoonline.library.injector.component.DaggerRssComponent;
import com.toan_itc.baoonline.library.injector.component.RssComponent;
import com.toan_itc.baoonline.library.injector.module.ListRssModule;
import com.toan_itc.baoonline.ui.fragment.HomeFragment;
import com.toan_itc.data.local.DatabaseRealm;

import javax.inject.Inject;

public class MainActivity extends BaseToolbar<RssComponent> {
    @Inject
    DatabaseRealm mDatabaseRealm;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void initViews() {
        addFagment(R.id.contentFragment, HomeFragment.newInstance());
    }
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected RssComponent injectDependencies() {
        return DaggerRssComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .listRssModule(new ListRssModule("http://vnexpress.net/rss/tin-moi-nhat.rss"))
                .build();
    }
    protected void remove_fragment(){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getName());
        if(fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
        Fragment menufragment = getSupportFragmentManager().findFragmentByTag(SkinBaseFragment.class.getName());
        if(menufragment != null) {
            getSupportFragmentManager().beginTransaction().remove(menufragment).commit();
        }
       /* Fragment fragmentseach = getSupportFragmentManager().findFragmentByTag(PlayerSearchFragment.class.getName());
        if(fragmentseach != null) {
            getSupportFragmentManager().beginTransaction().remove(fragmentseach).commit();
        }*/
    }
    @Override
    protected int getToolbarResId() {
        return 0;
    }

    @Override
    protected int getToolbarTitleResId() {
        return 0;
    }
}