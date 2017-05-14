package com.toan_itc.data.model.news;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public class Ngoisao extends RealmObject{
    @PrimaryKey
    private int Id;
    private String Title;
    private String Url;

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return Id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.Id = id;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.Title = title;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return Url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.Url = url;
    }
}
