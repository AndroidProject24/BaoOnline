package com.toan_itc.data.repository.data;


import com.toan_itc.data.model.rssrealm.RealmFeedItem;

import java.util.List;

import rx.Observable;

/**
 * Created by Toan.IT
 * Date: 13/09/2016
 * Email: huynhvantoan.itc@gmail.com
 */
public interface DataFetcher {

    Observable<List<RealmFeedItem>> getList();
}
