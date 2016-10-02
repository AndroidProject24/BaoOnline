package com.toan_itc.data.repository.data;


import android.support.annotation.NonNull;

import com.toan_itc.data.model.rssrealm.RealmFeedItem;

import java.util.List;

import bolts.Task;
import rx.Observable;

/**
 * Created by Toan.IT
 * Date: 13/09/2016
 * Email: huynhvantoan.itc@gmail.com
 */
public interface DataFetcher {

    Observable<List<RealmFeedItem>> getList();

	Observable<RealmFeedItem> getObject();

    void saveList(@NonNull Task task);

    void completeList(@NonNull Task task);

    void clearCompleted();

    void refreshList();

    void deleteAllList();

	void deleteObject(@NonNull String taskId);
}
