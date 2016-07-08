package com.toan_itc.baoonline.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.toan_it.library.skinloader.base.SkinBaseFragment;
import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.activity.BaseActivity;
import com.toan_itc.baoonline.library.data.local.DatabaseRealm;
import com.toan_itc.baoonline.ui.fragment.HomeFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {
    private BaseActivity mBaseActivity=this;
    @Inject
    DatabaseRealm mDatabaseRealm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
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
        mDatabaseRealm.setup();
        mDatabaseRealm.Get_News(this);
    }

    @Override
    protected void injectDependencies() {
       getComponent().inject(this);
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
}