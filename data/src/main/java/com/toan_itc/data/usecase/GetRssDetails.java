package com.toan_itc.data.usecase;

import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.repository.Repository;

import javax.inject.Inject;

import rx.Observable;

public class GetRssDetails extends UseCase {

  private final String userId;
  private final Repository mRepository;

  @Inject
  public GetRssDetails(String userId, Repository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.userId = userId;
    this.mRepository = repository;
  }

  @Override
  protected Observable buildUseCaseObservable() {
    return this.mRepository.GetRss(this.userId);
  }
}
