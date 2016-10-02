package com.toan_itc.data.repository.memory;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.toan_itc.data.model.rss.RssFeedItem;
import com.toan_itc.data.model.rssrealm.RealmFeedItem;
import com.toan_itc.data.repository.data.DataFetcher;
import com.toan_itc.data.repository.data.DataRetainer;

import java.util.List;

import bolts.Task;
import rx.Observable;

/**
 * Created by Toan.IT
 * Date: 13/09/2016
 * Email: huynhvantoan.itc@gmail.com
 */

public class MemoryDataSource implements DataFetcher, DataRetainer {
	@Override
	public Observable<List<RealmFeedItem>> getList() {
		return null;
	}

	@Override
	public Observable<RealmFeedItem> getObject() {
		return null;
	}

	@Override
	public void saveList(@NonNull Task task) {

	}

	@Override
	public void completeList(@NonNull Task task) {

	}

	@Override
	public void clearCompleted() {

	}

	@Override
	public void refreshList() {

	}

	@Override
	public void deleteAllList() {

	}

	@Override
	public void deleteObject(@NonNull String taskId) {

	}

	@Override
	public void retainList(@Nullable RssFeedItem feedItemList) {

	}

	@Override
	public boolean isCached() {
		return false;
	}
}
