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
package com.toan_itc.baoonline.library.injector.module;

import com.toan_itc.baoonline.library.injector.scope.ActivityScope;
import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.network.RestApi;
import com.toan_itc.data.usecase.GetRssDetails;
import com.toan_itc.data.usecase.GetRssList;
import com.toan_itc.data.usecase.UseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class ListRssModule {

  private String rss = "";

  public ListRssModule() {}

  public ListRssModule(String rss) {
    this.rss = rss;
  }

  @Provides
  @ActivityScope
  @Named("news")
  UseCase provideGetUserListUseCase(RestApi restApi, ThreadExecutor threadExecutor,
                                    PostExecutionThread postExecutionThread) {
    return new GetRssList(rss, restApi, threadExecutor, postExecutionThread);
  }

  @Provides
  @ActivityScope
  @Named("newsDetails")
  UseCase provideGetUserDetailsUseCase(RestApi restApi, ThreadExecutor threadExecutor,
                                       PostExecutionThread postExecutionThread) {
    return new GetRssDetails(rss, restApi, threadExecutor, postExecutionThread);
  }
}