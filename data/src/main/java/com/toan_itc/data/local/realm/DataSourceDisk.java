package com.toan_itc.data.local.realm;

import com.toan_itc.data.model.rss.RssFeedItem;

import java.util.List;

import rx.Observable;

/**
 * Created by Toan.IT
 * Date: 14/07/2016
 */
public interface DataSourceDisk {

    Observable<RssFeedItem> findByID(Integer identifier);

    Observable<List<RssFeedItem>> getBookmark();

    Observable<Integer> saveRss(RssFeedItem rssFeedItem);

    Observable<Boolean> removeRss(RssFeedItem rssFeedItem);
}
