package com.toan_itc.data.local.realm;

import com.toan_itc.data.model.rss.RssFeedItem;
import com.toan_itc.data.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Toan.IT
 * Date: 14/07/2016
 */
public class DataRepository implements Repository {
    private final DataSourceDisk dataSourceDisk;

    @Inject
    public DataRepository(DataSourceDisk namesDataSourceDisk){
        this.dataSourceDisk = namesDataSourceDisk;
    }

    @Override
    public Observable<List<RssFeedItem>> names() {
        return this.dataSourceDisk.getBookmark();
    }

    @Override
    public Observable<Boolean> delete(RssFeedItem names) {
        return this.dataSourceDisk.removeRss(names);
    }

    @Override
    public Observable<Integer> save(RssFeedItem names) {
        return this.dataSourceDisk.saveRss(names);
    }

    @Override
    public Observable<RssFeedItem> getName(Integer identifier) {
        return this.dataSourceDisk.findByID(identifier);
    }

}
