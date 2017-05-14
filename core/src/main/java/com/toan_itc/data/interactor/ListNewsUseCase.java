package com.toan_itc.data.interactor;

import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.model.rss.RssFeedItem;
import com.toan_itc.data.model.rssrealm.RealmFeedItem;
import com.toan_itc.data.repository.Repository;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link RssFeedItem}.
 */
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
    public Observable<List<RealmFeedItem>> buildUseCaseObservable() {
        return this.mRepository.getListHome(this.linkRss);
    }

    @Override
    protected Observable buildUseCaseObservableDB() {
        return this.mRepository.getChannel();
    }
}
