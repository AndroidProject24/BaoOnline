package com.toan_itc.data.usecase;

import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.network.RestApi;

import javax.inject.Inject;

import rx.Observable;

public class GetRssDetails extends UseCase {

  private final String userId;
  private final RestApi mRestApi;

  @Inject
  public GetRssDetails(String userId, RestApi restApi, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.userId = userId;
    this.mRestApi = restApi;
  }

  @Override
  protected Observable buildUseCaseObservable() {
    return this.mRestApi.GetRss(this.userId);
  }
}
