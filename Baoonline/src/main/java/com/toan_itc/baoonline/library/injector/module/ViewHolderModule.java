package com.toan_itc.baoonline.library.injector.module;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.toan_itc.baoonline.library.injector.qualifier.ActivityContext;
import com.toan_itc.baoonline.library.injector.scope.PerViewHolder;
import com.toan_itc.baoonline.navigation.ActivityNavigator;
import com.toan_itc.baoonline.navigation.Navigator;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewHolderModule {

	private final AppCompatActivity activity;

	public ViewHolderModule(View itemView) {
		activity = (AppCompatActivity) itemView.getContext();
	}

	@Provides
	@PerViewHolder
	@ActivityContext
	Context provideActivityContext() {
		return activity;
	}

	@Provides
	@PerViewHolder
	FragmentManager provideFragmentManager() {
		return activity.getSupportFragmentManager();
	}

	@Provides
	@PerViewHolder
	Navigator provideNavigator() {
		return new ActivityNavigator(activity);
	}

}
