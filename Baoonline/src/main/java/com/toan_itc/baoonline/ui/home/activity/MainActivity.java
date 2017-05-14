package com.toan_itc.baoonline.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.toan_itc.baoonline.base.SimpleActivity;
import com.toan_itc.baoonline.injector.component.ActivityComponent;
import com.toan_itc.baoonline.injector.scope.HasComponent;
import com.toan_itc.baoonline.interfaces.KeyListener;
import com.toan_itc.baoonline.interfaces.OnBackListener;
import com.toan_itc.baoonline.navigation.Navigator;
import com.toan_itc.baoonline.ui.home.fragment.ListNewsViewFragment;
import com.toan_itc.data.config.Constants;
import com.toan_itc.data.local.realm.RealmManager;
import com.toan_itc.data.model.news.Dantri;
import com.toan_itc.data.model.news.Dspl;
import com.toan_itc.data.model.news.Kenh14;
import com.toan_itc.data.model.news.Ngoisao;
import com.toan_itc.data.model.news.Tinhot;
import com.toan_itc.data.model.news.Vnexpress;
import com.toan_itc.data.theme.MaterialTheme;
import com.toan_itc.data.utils.ActivityCollector;
import com.toan_itc.data.utils.DoubleClickExit;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;


public class MainActivity extends SimpleActivity implements HasComponent<ActivityComponent>{
  @Inject
  RealmManager mRealmManager;
  @Inject
  Provider<Navigator> mNavigator;
  @BindView(R.id.spinner)
  AppCompatSpinner mSpinner;
  @BindView(R.id.toolbar)
  Toolbar mToolbar;
  private AccountHeader headerResult = null;
  private Drawer result = null;
  private static final String KEY_ARG_CURRENT_THEME = "KEY_ARG_CURRENT_THEME";

  private MaterialTheme mCurrentTheme;

  public static Intent newInstanceTheme(Context context, MaterialTheme currentTheme) {
    // Create an intent that will start the activity
    Intent intent = new Intent(context, MainActivity.class);

    // Get arguments passed in, if any
    Bundle args = intent.getExtras();
    if (args == null) {
      args = new Bundle();
    }

    // Add parameters to the argument bundle
    args.putSerializable(KEY_ARG_CURRENT_THEME, currentTheme);
    intent.putExtras(args);

    return intent;
  }
  @Override
  protected void initViews(Bundle savedInstanceState) {
    setTheme();
   /* Bundle bundle = new Bundle();
    bundle.putString(Constants.BUNLDE, mRealmManager.findFist(Vnexpress.class).getUrl());
    mNavigator.get().replaceFragment(R.id.content_main, new ListNewsViewFragment(),ListNewsViewFragment.class.getName(), bundle);*/
    initializeDrawer(savedInstanceState);
  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.activity_main;
  }

  @Override
  protected void initData() {
    initSpinner(1);
  }

  @Override
  protected void injectDependencies() {
    getComponent().inject(this);
  }

  @Override
  public ActivityComponent getComponent() {
    return getActivityComponent();
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
                  initSpinner(1);
                  bundle.putString(Constants.BUNLDE, mRealmManager.findFist(Tinhot.class).getUrl());
                  mNavigator.get().replaceFragment(R.id.content_main, new ListNewsViewFragment(),ListNewsViewFragment.class.getName(), bundle);
                } else if (drawerItem.getIdentifier() == 2) {
                  initSpinner(2);
                  bundle.putString(Constants.BUNLDE, mRealmManager.findFist(Vnexpress.class).getUrl());
                  mNavigator.get().replaceFragment(R.id.content_main, new ListNewsViewFragment(),ListNewsViewFragment.class.getName(), bundle);
                } else if (drawerItem.getIdentifier() == 3) {
                  initSpinner(3);
                  bundle.putString(Constants.BUNLDE, mRealmManager.findFist(Dantri.class).getUrl());
                  mNavigator.get().replaceFragment(R.id.content_main, new ListNewsViewFragment(),ListNewsViewFragment.class.getName(), bundle);
                } else if (drawerItem.getIdentifier() == 4) {
                  initSpinner(4);
                  bundle.putString(Constants.BUNLDE, mRealmManager.findFist(Ngoisao.class).getUrl());
                  mNavigator.get().replaceFragment(R.id.content_main, new ListNewsViewFragment(),ListNewsViewFragment.class.getName(), bundle);
                } else if (drawerItem.getIdentifier() == 5) {
                  initSpinner(5);
                  bundle.putString(Constants.BUNLDE, mRealmManager.findFist(Kenh14.class).getUrl());
                  mNavigator.get().replaceFragment(R.id.content_main, new ListNewsViewFragment(),ListNewsViewFragment.class.getName(), bundle);
                }else if(drawerItem.getIdentifier()==6){
                  initSpinner(6);
                  bundle.putString(Constants.BUNLDE, mRealmManager.findFist(Dspl.class).getUrl());
                  mNavigator.get().replaceFragment(R.id.content_main, new ListNewsViewFragment(),ListNewsViewFragment.class.getName(), bundle);
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
  public void onBackPressedSupport() {
    if (result != null && result.isDrawerOpen()) {
      result.closeDrawer();
    } else {
      String currentTag = getSupportFragmentManager().findFragmentById(R.id.content_main).getTag();
      if (currentTag.equals(ListNewsViewFragment.class.getName())) {
        if (!DoubleClickExit.check()) {
          Snackbar.make(mToolbar,R.string.msg_exit,Snackbar.LENGTH_SHORT).show();
        }else
          ActivityCollector.finishAll();
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
  @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
  private void initSpinner(int index){
    ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.item_in_list);
    List<String> listUrl=new ArrayList<>();
    switch (index){
      case 1:
        for (int i = 0; i < mRealmManager.size(Tinhot.class); i++) {
          adapter.add(mRealmManager.findAll(Tinhot.class).get(i).getTitle());
          listUrl.add(mRealmManager.findAll(Tinhot.class).get(i).getUrl());
        }
        break;
      case 2:
        for (int i = 0; i < mRealmManager.size(Vnexpress.class); i++) {
          adapter.add(mRealmManager.findAll(Vnexpress.class).get(i).getTitle());
          listUrl.add(mRealmManager.findAll(Vnexpress.class).get(i).getUrl());
        }
        break;
      case 3:
        for (int i = 0; i < mRealmManager.size(Dantri.class); i++) {
          adapter.add(mRealmManager.findAll(Dantri.class).get(i).getTitle());
          listUrl.add(mRealmManager.findAll(Dantri.class).get(i).getUrl());
        }
        break;
      case 4:
        for (int i = 0; i < mRealmManager.size(Ngoisao.class); i++) {
          adapter.add(mRealmManager.findAll(Ngoisao.class).get(i).getTitle());
          listUrl.add(mRealmManager.findAll(Ngoisao.class).get(i).getUrl());
        }
        break;
      case 5:
        for (int i = 0; i < mRealmManager.size(Kenh14.class); i++) {
          adapter.add(mRealmManager.findAll(Kenh14.class).get(i).getTitle());
          listUrl.add(mRealmManager.findAll(Kenh14.class).get(i).getUrl());
        }
        break;
      case 6:
        for (int i = 0; i < mRealmManager.size(Dspl.class); i++) {
          adapter.add(mRealmManager.findAll(Dspl.class).get(i).getTitle());
          listUrl.add(mRealmManager.findAll(Dspl.class).get(i).getUrl());
        }
        break;
    }

    adapter.setDropDownViewResource(R.layout.item_in_drop_down_list);
    mSpinner.setAdapter(adapter);
    mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNLDE,listUrl.get(position));
        mNavigator.get().replaceFragment(R.id.content_main, new ListNewsViewFragment(),ListNewsViewFragment.class.getName(), bundle);
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
  }
  private void setTheme(){
    Bundle args = getIntent().getExtras();

    // If no parameters were passed in, default them
    if (args == null) {
      mCurrentTheme = null;
    }
    // Otherwise, set incoming parameters
    else {
      mCurrentTheme = (MaterialTheme) args.getSerializable(KEY_ARG_CURRENT_THEME);
    }

    // If not set, default the theme
    if (mCurrentTheme == null) {
      mCurrentTheme = MaterialTheme.THEME_TEAL;
    }

    // Theme must be set before calling super or setContentView
    setTheme(mCurrentTheme.getThemeResId());

  }

}