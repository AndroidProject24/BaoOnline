package com.toan_itc.baoonline.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.toan_it.library.skinloader.base.SkinBaseFragment;
import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.baseactivity.BaseActivity;
import com.toan_itc.baoonline.library.injector.component.DaggerUserComponent;
import com.toan_itc.baoonline.library.injector.component.UserComponent;
import com.toan_itc.baoonline.library.injector.module.UserModule;
import com.toan_itc.baoonline.library.injector.scope.HasComponent;
import com.toan_itc.baoonline.ui.fragment.HomeFragment;
import com.toan_itc.data.local.DatabaseRealm;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements HasComponent<UserComponent> {
    private UserComponent userComponent;
    @Inject
    DatabaseRealm mDatabaseRealm;

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }


    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void initViews() {
        addFagment(getSupportFragmentManager(), HomeFragment.newInstance(), R.id.contentFragment);
    }
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void injectDependencies() {
        this.userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .userModule(new UserModule("http://www.24h.com.vn/upload/rss/tintuctrongngay.rss"))
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
    public UserComponent getComponent() {
        return userComponent;
    }
}