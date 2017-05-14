package com.toan_itc.baoonline.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.squareup.leakcanary.RefWatcher;
import com.toan_itc.baoonline.BaseApplication;
import com.toan_itc.baoonline.injector.component.ActivityComponent;
import com.toan_itc.baoonline.injector.component.ApplicationComponent;
import com.toan_itc.baoonline.injector.component.DaggerActivityComponent;
import com.toan_itc.baoonline.injector.module.ActivityModule;
import com.toan_itc.data.utils.ActivityCollector;
import com.toan_itc.data.utils.logger.Logger;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Toan.IT
 * Created by vantoan on 3/20/17.
 * Email: Huynhvantoan.itc@gmail.com
 */

public abstract class SimpleActivity extends SupportActivity {
  private ActivityComponent mActivityComponent;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(setLayoutResourceID());
    // changeStatusColor();
    injectDependencies();
    setNightOrDayMode();
    initNightModeSwitch();
    initViews(savedInstanceState);
    initData();
    ActivityCollector.addActivity(this);
  }
  //Intent activity
	/*public static void navigate(@NonNull AppCompatActivity activity, @NonNull View transitionImage,
	                            @NonNull Movie movie) {
		Intent intent = new Intent(activity, MovieDetailsActivity.class);
		intent.putExtra(EXTRA_MOVIE, movie);

		ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, IMAGE);
		ActivityCompat.startActivity(activity, intent, options.toBundle());
	}
*/
  @Override
  public void setContentView(int layoutResID) {
    super.setContentView(layoutResID);
    ButterKnife.bind(this);
  }

  protected abstract void initViews(Bundle bundle);

  protected abstract @LayoutRes int setLayoutResourceID();

  protected abstract void initData();

  protected abstract void injectDependencies();

  protected Snackbar snackBarBuild(View view,String message) {
    if (view== null) {
      throw new RuntimeException("This getLoadingTargetView not view!");
    }
    return Snackbar.make(view, message, Snackbar.LENGTH_LONG);
  }

  protected ApplicationComponent getApplicationComponent() {
    return ((BaseApplication) getApplication()).getApplicationComponent();
  }

  protected ActivityComponent getActivityComponent(){
    if (mActivityComponent == null) {
      mActivityComponent = DaggerActivityComponent.builder()
              .applicationComponent(getApplicationComponent())
              .activityModule(new ActivityModule(this))
              .build();
    }
    return mActivityComponent;
  }

  private void initNightModeSwitch() {
    //ColorfulNews-master
		/*if (this instanceof NewsActivity || this instanceof PhotoActivity) {
			MenuItem menuNightMode = mBaseNavView.getMenu().findItem(R.id.nav_night_mode);
			SwitchCompat dayNightSwitch = (SwitchCompat) MenuItemCompat
					.getActionView(menuNightMode);
			setCheckedState(dayNightSwitch);
			setCheckedEvent(dayNightSwitch);
		}*/
  }

  private void setNightOrDayMode() {
		/*if (MyUtils.isNightMode()) {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

			initNightView();
			mNightView.setBackgroundResource(R.color.night_mask);
		} else {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
		}*/
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Logger.d("BaseActivity:onDestroy");
    RefWatcher refWatcher = BaseApplication.getRefWatcher();
    refWatcher.watch(this);
    ActivityCollector.removeActivity(this);
  }
}
