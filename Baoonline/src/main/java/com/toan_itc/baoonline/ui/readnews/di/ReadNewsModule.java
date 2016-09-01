package com.toan_itc.baoonline.ui.readnews.di;

import com.toan_itc.baoonline.library.injector.scope.PerActivity;
import com.toan_itc.baoonline.ui.readnews.mvp.ReadNewsUseCase;
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
public class ReadNewsModule {

  private String link = "";

  public ReadNewsModule(String link) {
    this.link = link;
  }

  @Provides
  @PerActivity
  UseCase provideGetUserDetailsUseCase(Repository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    return new ReadNewsUseCase(link, repository, threadExecutor, postExecutionThread);
  }
}