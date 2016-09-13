package com.toan_itc.data.repository.remote;

import com.toan_itc.data.model.rssrealm.RealmFeedItem;
import com.toan_itc.data.repository.data.DataFetcher;

import java.util.List;

import rx.Observable;

/**
 * Created by Toan.IT
 * Date: 13/09/2016
 * Email: huynhvantoan.itc@gmail.com
 */

public class RemoteData implements DataFetcher {
	@Override
	public Observable<List<RealmFeedItem>> getList() {
		return null;
	}
}
