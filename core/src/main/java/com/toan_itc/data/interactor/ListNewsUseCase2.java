/*
package com.toan_itc.data.interactor;

import com.toan_itc.data.config.StatusCodes;
import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.model.rss.RssFeedItem;
import com.toan_itc.data.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

*/
/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link RssFeedItem}.
 *//*

public class ListNewsUseCase2 extends UseCase<StatusCodes.statusCodes, Void> {
    private final String linkRss;
    private final Repository mRepository;

    @Inject
    public ListNewsUseCase2(String linkRss, Repository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.linkRss=linkRss;
        this.mRepository = repository;
    }

    @Override
    public Observable<StatusCodes.statusCodes> buildUseCaseObservable(Void unused) {
        return this.mRepository.getRss(this.linkRss,false);
    }
}
*/
