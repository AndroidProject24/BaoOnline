package com.toan_itc.baoonline.library.injector.module;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.toan_itc.baoonline.library.injector.qualifier.ActivityContext;
import com.toan_itc.baoonline.library.injector.qualifier.ChildFragmentManager;
import com.toan_itc.baoonline.library.injector.qualifier.DefaultFragmentManager;
import com.toan_itc.baoonline.library.injector.scope.PerFragment;
import com.toan_itc.baoonline.navigation.FragmentNavigator;
import com.toan_itc.baoonline.navigation.Navigator;

import dagger.Module;
import dagger.Provides;

/* Copyright 2016 Patrick Löwenstein
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */
@Module
public class FragmentModule {

	private final Fragment mFragment;

	public FragmentModule(Fragment fragment) {
		mFragment = fragment;
	}

	@Provides
	@PerFragment
	@ActivityContext
	Context provideActivityContext() {
		return mFragment.getActivity();
	}

	@Provides
	@PerFragment
	@DefaultFragmentManager
	FragmentManager provideDefaultFragmentManager() {
		return mFragment.getFragmentManager();
	}

	@Provides
	@PerFragment
	@ChildFragmentManager
	FragmentManager provideChildFragmentManager() {
		return mFragment.getChildFragmentManager();
	}

	@Provides
	@PerFragment
	Navigator provideNavigator() {
		return new FragmentNavigator(mFragment);
	}

}
