package com.toan_itc.data.repository;

import com.toan_itc.data.model.rss.RssFeedItem;

import java.util.List;

import rx.Observable;

/**
 * Created by Toan.IT
 * Date: 14/07/2016
 */
public interface Repository {

    Observable<List<RssFeedItem>> names();

    Observable<Boolean> delete(RssFeedItem names);

    Observable<Integer> save(RssFeedItem names);

    Observable<RssFeedItem> getName(Integer identifier);
}
