package com.toan_itc.baoonline.ui.splash.activity;

import android.os.Bundle;

import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.base.BaseActivity;
import com.toan_itc.baoonline.library.injector.component.ActivityComponent;
import com.toan_itc.baoonline.library.injector.component.DaggerActivityComponent;
import com.toan_itc.baoonline.library.injector.module.ActivityModule;
import com.toan_itc.baoonline.library.injector.scope.HasComponent;
import com.toan_itc.baoonline.navigation.Navigator;
import com.toan_itc.baoonline.ui.home.activity.MainActivity;
import com.toan_itc.data.executor.DefaultSubscriber;
import com.toan_itc.data.libs.splash.ParticleView;
import com.toan_itc.data.libs.view.StateLayout;
import com.toan_itc.data.rxjava.RxUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Toan.IT
 * Date: 22/05/2016
 */
public class SplashScreen extends BaseActivity implements HasComponent<ActivityComponent> {
    @BindView(R.id.splash)
    ParticleView mParticleView;
    @Inject
    Provider<Navigator> mNavigator;
    @Inject
    RxUtils mRxUtils;
    private ActivityComponent mActivityComponent;
    private Subscription subscription;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        subscription= Observable.timer(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultSubscriber<Long>(){
                    @Override
                    public void onCompleted() {
                        mParticleView.startAnim();
                    }
                });
        mRxUtils.addCompositeSubscription(subscription);
        mParticleView.setOnClickListener(view -> mNavigator.get().startActivity(MainActivity.class));
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        subscription= Observable.timer(3, TimeUnit.SECONDS)
                .subscribe(new DefaultSubscriber<Long>(){
                    @Override
                    public void onCompleted() {
                        finish();
                        mNavigator.get().startActivity(MainActivity.class);
                    }
                });
        mRxUtils.addCompositeSubscription(subscription);
    }

    @Override
    protected void injectDependencies() {
        getComponent().inject(this);
    }

    @Override
    protected StateLayout getLoadingTargetView() {
        return null;
    }

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
    protected void onDestroy() {
        super.onDestroy();
        mRxUtils.clearCompositeSubscription();
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
