package com.toan_itc.data.model.news;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Toan.IT
 * Date: 09/07/2016
 */
public class ListNews extends RealmObject{
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
