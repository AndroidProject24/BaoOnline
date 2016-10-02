package com.toan_itc.baoonline.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.squareup.leakcanary.RefWatcher;
import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.BaseApplication;
import com.toan_itc.baoonline.library.base.view.EmptyView;
import com.toan_itc.baoonline.library.base.view.ErrorView;
import com.toan_itc.baoonline.library.base.view.LoadView;
import com.toan_itc.baoonline.library.injector.component.ApplicationComponent;
import com.toan_itc.baoonline.library.injector.module.ActivityModule;
import com.toan_itc.data.libs.view.StateLayout;
import com.toan_itc.data.local.realm.RealmManager;
import com.toan_itc.data.utils.ActivityCollector;
import com.toan_itc.data.utils.logger.Logger;

import javax.inject.Inject;

import butterknife.ButterKnife;


/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public abstract class BaseActivity extends AppCompatActivity implements LoadView, ErrorView, EmptyView {
	@Inject
	RealmManager mRealmManager;
	private StateLayout mStateLayout = null;

	public enum TransitionMode {
		LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		animation();
		setContentView(setLayoutResourceID());
		getApplicationComponent().inject(this);
		injectDependencies();
		// changeStatusColor();
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
		if (null != getLoadingTargetView()) {
			mStateLayout = getLoadingTargetView();
		}
	}

	protected abstract void initViews(Bundle bundle);

	protected abstract @LayoutRes int setLayoutResourceID();

	protected abstract void initData();

	protected abstract void injectDependencies();

	protected abstract StateLayout getLoadingTargetView();

	protected void toggleShowLoading(boolean toggle) {
		if (null == mStateLayout) {
			throw new IllegalArgumentException("You must return a right target view for loading");
		}
		if (toggle)
			mStateLayout.showProgressView();
		else
			mStateLayout.showContentView();
	}

	protected void toggleShowEmpty(String msg, View.OnClickListener onClickListener) {
		if (null == mStateLayout) {
			throw new IllegalArgumentException("You must return a right target view for Empty");
		}
		mStateLayout.showEmptyView(msg);
		if (onClickListener != null) {
			mStateLayout.setEmptyAction(onClickListener);
		}
	}

	protected void toggleShowError(String msg, View.OnClickListener onClickListener) {
		if (null == mStateLayout) {
			throw new IllegalArgumentException("You must return a right target view for Error");
		}
		mStateLayout.showErrorView(msg);
		if (onClickListener != null) {
			mStateLayout.setErrorAction(onClickListener);
		}
	}

	@Override
	public void showEmptyView(String message) {
		toggleShowEmpty(message, null);
	}

	@Override
	public void showError(String message) {
		toggleShowError(message, null);
	}

	@Override
	public void showLoading() {
		toggleShowLoading(true);
	}

	@Override
	public void hideLoading() {
		toggleShowLoading(false);
	}

	public Context getContext(){
		return getApplicationComponent().context();
	}
	public RealmManager getRealmManager() {
		return mRealmManager;
	}

	protected ApplicationComponent getApplicationComponent() {
		return ((BaseApplication) getApplication()).getApplicationComponent();
	}

	protected ActivityModule getActivityModule() {
		return new ActivityModule(this);
	}

	private TransitionMode getOverridePendingTransitionMode() {
		return TransitionMode.RIGHT;
	}

	protected Snackbar snackBarBuild(String message) {
		if (getLoadingTargetView() == null) {
			throw new RuntimeException("This getLoadingTargetView not view!");
		}
		return Snackbar.make(getLoadingTargetView(), message, Snackbar.LENGTH_LONG);
	}

	protected boolean toggleOverridePendingTransition() {
		return true;
	}

	private void animation() {
		if (toggleOverridePendingTransition()) {

			switch (getOverridePendingTransitionMode()) {
				case LEFT:
					overridePendingTransition(R.anim.left_in, R.anim.left_out);
					break;
				case RIGHT:
					overridePendingTransition(R.anim.left_in, R.anim.right_out);
					break;
				case TOP:
					overridePendingTransition(R.anim.top_in, R.anim.top_out);
					break;
				case BOTTOM:
					overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
					break;
				case SCALE:
					overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
					break;
				case FADE:
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
					break;
			}
		}
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

	@Override
	public void finish() {
		super.finish();
		animation();
	}
}
