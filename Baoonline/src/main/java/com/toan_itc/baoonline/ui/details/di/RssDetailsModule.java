package com.toan_itc.baoonline.ui.details.di;

import com.toan_itc.baoonline.library.injector.scope.PerFragment;
import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.repository.Repository;
import com.toan_itc.data.usecase.GetRssDetails;
import com.toan_itc.data.usecase.UseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class RssDetailsModule {

  private String rss = "";

  public RssDetailsModule(String rss) {
    this.rss = rss;
  }


  @Provides
  @PerFragment
  UseCase provideGetUserDetailsUseCase(Repository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    return new GetRssDetails(rss, repository, threadExecutor, postExecutionThread);
  }
}