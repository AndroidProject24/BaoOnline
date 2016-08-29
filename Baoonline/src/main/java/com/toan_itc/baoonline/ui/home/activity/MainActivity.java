package com.toan_itc.baoonline.ui.home.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.base.BaseActivity;
import com.toan_itc.baoonline.library.injector.component.ActivityComponent;
import com.toan_itc.baoonline.library.injector.component.DaggerActivityComponent;
import com.toan_itc.baoonline.library.injector.module.ActivityModule;
import com.toan_itc.baoonline.library.injector.scope.HasComponent;
import com.toan_itc.baoonline.listener.KeyListener;
import com.toan_itc.baoonline.listener.OnBackListener;
import com.toan_itc.baoonline.navigation.Navigator;
import com.toan_itc.baoonline.ui.home.fragment.ListNewsFragment;
import com.toan_itc.data.model.news.Dantri;
import com.toan_itc.data.model.news.Dspl;
import com.toan_itc.data.model.news.Kenh14;
import com.toan_itc.data.model.news.Ngoisao;
import com.toan_itc.data.model.news.Tinhot;
import com.toan_itc.data.model.news.Vnexpress;
import com.toan_itc.data.utils.Constants;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements HasComponent<ActivityComponent>{
    @BindView(R.id.spinner)
    AppCompatSpinner mSpinner;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @Inject
    Provider<Navigator> mNavigator;
    private ActivityComponent mActivityComponent;
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private boolean doubleBackToExitPressedOnce;
    @Override
    protected void initViews(Bundle savedInstanceState) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNLDE, getRealmManager().findFist(Tinhot.class).getUrl());
        mNavigator.get().replaceFragment(R.id.content_main, new ListNewsFragment(),ListNewsFragment.class.getName(), bundle);
        initializeDrawer(savedInstanceState);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.item_in_list);
        for (int i = 0; i < getRealmManager().size(Tinhot.class); i++) {
            adapter.add(getRealmManager().findAll(Tinhot.class).get(i).getTitle());
        }
        adapter.setDropDownViewResource(R.layout.item_in_drop_down_list);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.BUNLDE, getRealmManager().findAll(Tinhot.class).get(position).getUrl());
                mNavigator.get().replaceFragment(R.id.content_main, new ListNewsFragment(),ListNewsFragment.class.getName(), bundle);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void injectDependencies() {
        getComponent().inject(this);
    }

    @Override
    protected View getLoadingTargetView() {
        return ButterKnife.findById(this, R.id.content_main);
    }

    @Override
    public ActivityComponent getComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return mActivityComponent;
    }

    private void initializeDrawer(Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);
        setTitle(null);
        final IProfile profile = new ProfileDrawerItem().withName("Huỳnh văn toàn").withEmail("huynhvantoan.itc@gmail.com").withIcon("https://fbcdn-photos-c-a.akamaihd.net/hphotos-ak-xfa1/v/t1.0-0/p206x206/11751426_794373917343882_7256246783339720174_n.jpg?oh=ae7a8b6ffbc38bd09c7215313ca79d09&oe=5808AD45&__gda__=1475369879_a3724b4a61997de0523aba09cfd19f82").withIdentifier(100);
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.background_slider)
                .addProfiles(profile)
                .withSavedInstance(savedInstanceState)
                .build();
        result = new DrawerBuilder(this)
                .withActivity(this)
                .withToolbar(mToolbar)
                .withHasStableIds(true)
                .withAccountHeader(headerResult)
                .withRootView(R.id.drawer_container)
                .withDisplayBelowStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.menu_tinhot).withIcon(R.mipmap.ic_launcher).withIdentifier(1).withSetSelected(true),
                        new PrimaryDrawerItem().withName(R.string.menu_vnexpress).withIcon(R.mipmap.ic_vnexpress).withIdentifier(2).withSetSelected(true),
                        new PrimaryDrawerItem().withName(R.string.menu_dantri).withIcon(R.mipmap.ic_dantri).withIdentifier(3).withSetSelected(true),
                        new PrimaryDrawerItem().withName(R.string.menu_ngoisao).withIcon(R.mipmap.ic_ngoisao).withIdentifier(4).withSetSelected(true).withEnabled(true),
                        new PrimaryDrawerItem().withName(R.string.menu_kenh14).withIcon(R.mipmap.ic_kenh14).withIdentifier(5).withSetSelected(true),
                        new PrimaryDrawerItem().withName(R.string.menu_dspl).withIcon(R.mipmap.ic_doisong).withIdentifier(6).withSetSelected(true).withEnabled(true),
                        new SectionDrawerItem().withName("Help"),
                        new SectionDrawerItem().withName("Setting")
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    if (drawerItem != null) {
                        Bundle bundle = new Bundle();
                        if (drawerItem.getIdentifier() == 1) {
                            bundle.putString(Constants.BUNLDE, getRealmManager().findFist(Tinhot.class).getUrl());
                            mNavigator.get().replaceFragment(R.id.content_main, new ListNewsFragment(),ListNewsFragment.class.getName(), bundle);
                        } else if (drawerItem.getIdentifier() == 2) {
                            bundle.putString(Constants.BUNLDE, getRealmManager().findFist(Vnexpress.class).getUrl());
                            mNavigator.get().replaceFragment(R.id.content_main, new ListNewsFragment(),ListNewsFragment.class.getName(), bundle);
                        } else if (drawerItem.getIdentifier() == 3) {
                            bundle.putString(Constants.BUNLDE, getRealmManager().findFist(Dantri.class).getUrl());
                            mNavigator.get().replaceFragment(R.id.content_main, new ListNewsFragment(),ListNewsFragment.class.getName(), bundle);
                        } else if (drawerItem.getIdentifier() == 4) {
                            bundle.putString(Constants.BUNLDE, getRealmManager().findFist(Ngoisao.class).getUrl());
                            mNavigator.get().replaceFragment(R.id.content_main, new ListNewsFragment(),ListNewsFragment.class.getName(), bundle);
                        } else if (drawerItem.getIdentifier() == 5) {
                            bundle.putString(Constants.BUNLDE, getRealmManager().findFist(Kenh14.class).getUrl());
                            mNavigator.get().replaceFragment(R.id.content_main, new ListNewsFragment(),ListNewsFragment.class.getName(), bundle);
                        }else if(drawerItem.getIdentifier()==6){
                            bundle.putString(Constants.BUNLDE, getRealmManager().findFist(Dspl.class).getUrl());
                            mNavigator.get().replaceFragment(R.id.content_main, new ListNewsFragment(),ListNewsFragment.class.getName(), bundle);
                        }
                    }
                    return false;
                })
                .withSavedInstance(savedInstanceState)
                .build();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = result.saveInstanceState(outState);
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            String currentTag = getSupportFragmentManager().findFragmentById(R.id.content_main).getTag();
            if (currentTag.equals(ListNewsFragment.class.getName())) {
                if (doubleBackToExitPressedOnce) {
                    finish();
                }
                this.doubleBackToExitPressedOnce = true;
                Snackbar.make(mToolbar,R.string.msg_exit,Snackbar.LENGTH_SHORT).show();
                new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
            } else {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_main);
                if (fragment instanceof OnBackListener) {
                    ((OnBackListener) fragment).onBackPress();
                }
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode()==KeyEvent.KEYCODE_BACK ) {
            onBackPressed();
        }
        else if(getSupportFragmentManager().findFragmentById(R.id.content_main) instanceof KeyListener) {
            KeyListener keyListener = (KeyListener) getSupportFragmentManager().findFragmentById(R.id.content_main);
            keyListener.onKeyDown(keyCode, event);
        }
        return false;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityComponent = null;
    }
}