/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.toan_itc.baoonline.ui.home.di;

import com.toan_itc.baoonline.library.injector.scope.PerFragment;
import com.toan_itc.data.repository.remote.ListNewsUseCase;
import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.repository.Repository;
import com.toan_itc.data.usecase.UseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class ListNewsModule {

  private String rss = "";

  public ListNewsModule(String rss) {
    this.rss = rss;
  }

  @Provides
  @PerFragment
  UseCase provideGetUserListUseCase(Repository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    return new ListNewsUseCase(rss, repository, threadExecutor, postExecutionThread);
  }
}