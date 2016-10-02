package com.toan_itc.data.repository.remote;

import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.repository.Repository;
import com.toan_itc.data.usecase.UseCase;

import javax.inject.Inject;

import rx.Observable;

public class ListNewsUseCase extends UseCase {
    private final String linkRss;
    private final Repository mRepository;

    @Inject
    public ListNewsUseCase(String linkRss, Repository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.linkRss=linkRss;
        this.mRepository = repository;
    }


    @Override
    public Observable buildUseCaseObservable() {
        return this.mRepository.getRss(this.linkRss,false);
    }

    @Override
    protected Observable buildUseCaseObservableDB() {
        return this.mRepository.getChannel();
    }
}
