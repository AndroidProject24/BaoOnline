package com.toan_itc.data.repository.data;

import android.support.annotation.Nullable;

import com.toan_itc.data.model.rss.RssFeedItem;

/**
 * Created by Toan.IT
 * Date: 13/09/2016
 * Email: huynhvantoan.itc@gmail.com
 */
public interface DataRetainer {

    void retainList(@Nullable RssFeedItem feedItemList);

    boolean isCached();
}
