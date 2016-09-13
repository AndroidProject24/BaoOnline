package com.toan_itc.data.repository.memory;

import com.toan_itc.data.model.rssrealm.RealmFeedItem;
import com.toan_itc.data.repository.data.DataFetcher;
import com.toan_itc.data.repository.data.DataRetainer;

import java.util.List;

import rx.Observable;

/**
 * Created by Toan.IT
 * Date: 13/09/2016
 * Email: huynhvantoan.itc@gmail.com
 */

public class MemoryData implements DataFetcher, DataRetainer {
	@Override
	public Observable<List<RealmFeedItem>> getList() {
		return null;
	}

	@Override
	public void retainList(List<RealmFeedItem> feedItemList) {

	}

	@Override
	public boolean isCached() {
		return false;
	}
}
