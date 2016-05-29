package com.toan_it.library.library.mvp.model.news;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public class Data extends RealmObject{
    @PrimaryKey
    private String id;
    private String title;
    private String url;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
