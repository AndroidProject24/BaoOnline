package com.toan_itc.baoonline.library.mvp.model.news;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Toan.IT on 5/2/16.
 */
public class ListNews extends RealmObject{
    @PrimaryKey
    private String id="1";
    private RealmList<com.toan_itc.baoonline.library.mvp.model.news.News> News = new RealmList<>();
    /**
     *
     * @return
     * The News
     */
    public RealmList<com.toan_itc.baoonline.library.mvp.model.news.News> getNews() {
        return News;
    }

    /**
     *
     * @param News
     * The News
     */
    public void setNews(RealmList<com.toan_itc.baoonline.library.mvp.model.news.News> News) {
        this.News = News;
    }
}
