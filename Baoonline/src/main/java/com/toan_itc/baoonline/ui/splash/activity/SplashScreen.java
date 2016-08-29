package com.toan_itc.baoonline.ui.splash.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.base.BaseActivity;
import com.toan_itc.baoonline.library.injector.component.ActivityComponent;
import com.toan_itc.baoonline.library.injector.component.DaggerActivityComponent;
import com.toan_itc.baoonline.library.injector.module.ActivityModule;
import com.toan_itc.baoonline.library.injector.scope.HasComponent;
import com.toan_itc.baoonline.navigation.Navigator;
import com.toan_itc.baoonline.ui.home.activity.MainActivity;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import rx.Subscription;

/**
 * Created by Toan.IT
 * Date: 22/05/2016
 */
public class SplashScreen extends BaseActivity implements HasComponent<ActivityComponent> {
    Subscription subscription;
    @BindView(R.id.img_splash)
    ImageView mImgSplash;
    @Inject
    Provider<Navigator> mNavigator;
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initViews(Bundle savedInstanceState) {
        mImgSplash.setOnClickListener(view -> mNavigator.get().startActivity(MainActivity.class));
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getNavigator().navigateToMainActivity(SplashScreen.this);
            }
        },2000);*/
    }

    @Override
    protected void injectDependencies() {
        mActivityComponent.inject(this);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
/*
    @Override
    protected RssActivityComponent injectDependencies() {
        return null;
    }*/

    @Override
    protected void onStart() {
        super.onStart();
       /* subscription = PlayStore.checkForUpdates(this, "Google-Play-Android-Developer.json", PlayStore.ReleaseType.BETA)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    String msg;
                    if (s == null) {
                        msg = "No updates.";
                    } else {
                        msg = s.toString();
                    }
                    new AlertDialog.Builder(this)
                            .setMessage(msg)
                            .setPositiveButton(android.R.string.ok, null)
                            .show();
                }, Throwable::printStackTrace);*/
    }

    @Override
    protected void onStop() {
        super.onStop();
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
}
