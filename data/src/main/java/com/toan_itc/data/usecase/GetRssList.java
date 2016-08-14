package com.toan_itc.data.usecase;

import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.repository.Repository;

import javax.inject.Inject;

import rx.Observable;

public class GetRssList extends UseCase {
    private final String linkRss;
    private final Repository mRepository;

    @Inject
    public GetRssList(String linkRss, Repository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.linkRss=linkRss;
        this.mRepository = repository;
    }


    @Override
    public Observable buildUseCaseObservable() {
        return this.mRepository.GetRss(this.linkRss);
    }
}
