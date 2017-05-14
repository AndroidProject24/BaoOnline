package com.toan_itc.baoonline.base;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
/**
 * Toan.IT
 * Created by vantoan on 3/20/17.
 * Email: Huynhvantoan.itc@gmail.com
 */
public abstract class BaseNavActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mAttachDrawerLayout;
    private Class mClass;

    protected void initDrawerLayout(DrawerLayout drawerLayout, NavigationView navView, Toolbar toolbar) {
        mAttachDrawerLayout = drawerLayout;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            drawerLayout.setFitsSystemWindows(true);
            drawerLayout.setClipToPadding(false);
        }

       // ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
              //  this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawerLayout.setDrawerListener(toggle);
        //toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
        mAttachDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                if (mClass != null) {
                    Intent intent = new Intent(BaseNavActivity.this, mClass);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    BaseNavActivity.this.startActivity(intent);
                  //  overridePendingTransition(R.anim.fade_in_activity, R.anim.fade_out_activity);
                    mClass = null;
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mAttachDrawerLayout.closeDrawer(GravityCompat.START);
        if (item.isChecked()) {
            return true;
        }

        switch (item.getItemId()) {
           /* case R.id.nav_photos:
                mClass = PhotosActivity.class;
                break;
            case R.id.nav_news:
                mClass = MainActivity.class;
                break;*/
        }
        return false;
    }

    @Override
    public void onBackPressedSupport() {
        if (mAttachDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mAttachDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressedSupport();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        overridePendingTransition(0, 0);
    }
}
