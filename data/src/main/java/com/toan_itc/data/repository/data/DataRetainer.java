package com.toan_itc.data.repository.data;

import com.toan_itc.data.model.rssrealm.RealmFeedItem;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 13/09/2016
 * Email: huynhvantoan.itc@gmail.com
 */
public interface DataRetainer {

    void retainList(List<RealmFeedItem> feedItemList);

    boolean isCached();
}
