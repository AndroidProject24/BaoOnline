package com.toan_itc.baoonline.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.toan_it.library.library.BaseApplication;
import com.toan_it.library.library.activity.BaseActivity;
import com.toan_it.library.library.data.local.DatabaseRealm;
import com.toan_it.library.library.data.local.PreferencesHelper;
import com.toan_it.library.library.data.rxjava.RxBus;
import com.toan_it.library.skinloader.base.SkinBaseFragment;
import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.injector.ActivityComponent;
import com.toan_itc.baoonline.injector.DaggerActivityComponent;
import com.toan_itc.baoonline.ui.fragment.HomeFragment;

public class MainActivity extends BaseActivity{
    private BaseActivity mBaseActivity=this;
    private DatabaseRealm mDatabaseRealm;
    private PreferencesHelper mPreferencesHelper;
    private RxBus rxBus;
    private ActivityComponent mActivityComponent;
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
        addFagment(getSupportFragmentManager(), HomeFragment.newInstance(), R.id.contentFrame);
    }
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mDatabaseRealm=BaseApplication.getInstance().getApplicationComponent().mDatabaseRealm();
        mDatabaseRealm.setup();
        mDatabaseRealm.Get_News(this);
    }

    @Override
    protected void injectDependencies() {
        if (mActivityComponent == null) {
            mActivityComponent= DaggerActivityComponent.builder()
                    .applicationComponent(BaseApplication.get(this).getApplicationComponent())
                    .build();
        }
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

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }
}