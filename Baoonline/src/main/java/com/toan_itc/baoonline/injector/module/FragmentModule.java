package com.toan_itc.baoonline.injector.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.toan_itc.baoonline.injector.qualifier.ChildFragmentManager;
import com.toan_itc.baoonline.injector.scope.PerFragment;
import com.toan_itc.baoonline.navigation.ChildFragmentNavigator;
import com.toan_itc.baoonline.navigation.FragmentNavigator;

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
	@ChildFragmentManager
	FragmentManager provideChildFragmentManager() { return mFragment.getChildFragmentManager(); }

	@Provides
	@PerFragment
	FragmentNavigator provideFragmentNavigator() { return new ChildFragmentNavigator(mFragment); }

}
