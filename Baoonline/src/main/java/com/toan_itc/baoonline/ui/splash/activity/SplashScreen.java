package com.toan_itc.baoonline.ui.splash.activity;

import android.os.Bundle;

import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.base.SimpleActivity;
import com.toan_itc.baoonline.injector.component.ActivityComponent;
import com.toan_itc.baoonline.injector.scope.HasComponent;
import com.toan_itc.baoonline.navigation.Navigator;
import com.toan_itc.baoonline.ui.home.activity.MainActivity;
import com.toan_itc.data.interactor.DefaultObserver;
import com.toan_itc.data.libs.splash.ParticleView;
import com.toan_itc.data.rxjava.RxUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Toan.IT
 * Date: 22/05/2016
 */
public class SplashScreen extends SimpleActivity implements HasComponent<ActivityComponent>{
  @BindView(R.id.splash)
  ParticleView mParticleView;
  @Inject
  RxUtils mRxUtils;
  @Inject
  Provider<Navigator> mNavigator;
  @Override
  protected void initViews(Bundle savedInstanceState) {
    mRxUtils.addCompositeSubscription(Observable.timer(200, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new DefaultObserver<Long>(){
              @Override
              public void onCompleted() {
                mParticleView.startAnim();
              }
            }));
    mParticleView.setOnClickListener(view -> mNavigator.get().startActivity(MainActivity.class));
  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.activity_splash;
  }

  @Override
  protected void initData() {
    mRxUtils.addCompositeSubscription(Observable.timer(3, TimeUnit.SECONDS)
            .subscribe(new DefaultObserver<Long>(){
              @Override
              public void onCompleted() {
                mNavigator.get().startActivity(MainActivity.class);
                //mNavigator.get().finishActivity();
              }
            }));
  }

  @Override
  protected void injectDependencies() {
    getComponent().inject(this);
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
    mRxUtils.unCompositeSubscription();
  }

  @Override
  public ActivityComponent getComponent() {
    return getActivityComponent();
  }
}
