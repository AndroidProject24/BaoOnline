package com.toan_itc.data.repository;

import android.support.annotation.NonNull;
import bolts.Task;
import com.toan_itc.data.model.rssrealm.RealmChannel;
import com.toan_itc.data.model.rssrealm.RealmFeedItem;
import java.util.List;
import rx.Observable;

/**
 * Created by vantoan on 2/22/17.
 * Email: huynhvantoan.itc@gmail.com
 */

public interface DataSource {

  Observable<List<RealmFeedItem>> getListHome(@NonNull String url);

  Observable<RealmFeedItem> getObject(@NonNull String FeedId);

  void saveListHome(@NonNull RealmChannel realmFeedItem);

  void completeTask(@NonNull Task task);

  void completeTask(@NonNull String taskId);

  void activateTask(@NonNull Task task);

  void activateTask(@NonNull String taskId);

  void clearCompletedTasks();

  void refreshTasks();

  void deleteAllTasks();

  void deleteTask(@NonNull String taskId);
}
