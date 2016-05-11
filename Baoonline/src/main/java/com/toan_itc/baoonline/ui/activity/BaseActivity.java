package com.toan_itc.baoonline.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.toan_itc.baoonline.BaoOnlineApplication;
import com.toan_itc.baoonline.data.local.DatabaseRealm;
import com.toan_itc.baoonline.injector.component.ActivityComponent;
import com.toan_itc.baoonline.injector.component.DaggerActivityComponent;
import com.toan_itc.baoonline.utils.StatusBarUtil;

import butterknife.ButterKnife;

/**
 * Created by Toan.IT on 5/1/16.
 */
public class BaseActivity extends AppCompatActivity {
    private int mDayNightMode = AppCompatDelegate.MODE_NIGHT_AUTO;
    private ActivityComponent mComponent;
    public DatabaseRealm mDatabaseRealm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(this);
        initInjector();
        initData();
        /*getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        recreate();
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        recreate();*/
    }
    private void initData(){
        mDatabaseRealm=getApp().getApplicationComponent().mDatabaseRealm();
        mDatabaseRealm.setup();
        mDatabaseRealm.Get_News(this);
    }
    private void initInjector(){
        mComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApp().getApplicationComponent())
                .build();
    }
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected ActivityComponent getComponent() {
        return mComponent;
    }

    protected BaoOnlineApplication getApp() {
        return (BaoOnlineApplication) getApplicationContext();
    }
    @Override
    protected void onResume() {
        super.onResume();
        int uiMode = getResources().getConfiguration().uiMode;
        int dayNightUiMode = uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if (dayNightUiMode == Configuration.UI_MODE_NIGHT_NO) {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_NO;
            //MODE_NIGHT_NO
        } else if (dayNightUiMode == Configuration.UI_MODE_NIGHT_YES) {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_YES;
            //MODE_NIGHT_YES
        } else {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_AUTO;
            //MODE_NIGHT_AUTO
        }
    }
}
