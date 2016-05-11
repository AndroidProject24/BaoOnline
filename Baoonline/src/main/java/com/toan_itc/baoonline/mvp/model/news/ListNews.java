package com.toan_itc.baoonline.mvp.model.news;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Toan.IT on 5/2/16.
 */
public class ListNews extends RealmObject{
    @PrimaryKey
    private String id="1";
    private RealmList<News> News = new RealmList<>();
    /**
     *
     * @return
     * The News
     */
    public RealmList<News> getNews() {
        return News;
    }

    /**
     *
     * @param News
     * The News
     */
    public void setNews(RealmList<News> News) {
        this.News = News;
    }
}
