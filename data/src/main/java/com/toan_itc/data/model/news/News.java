package com.toan_itc.data.model.news;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Toan.IT
 * Date: 09/07/2016
 */
public class News extends RealmObject{
    @PrimaryKey
    private long Id;
    private String Title;
    private RealmList<Data> Data = new RealmList<>();
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    /**
     *
     * @return
     * The Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @param Title
     * The Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     *
     * @return
     * The Data
     */
    public RealmList<Data> getData() {
        return Data;
    }

    /**
     *
     * @param Data
     * The Data
     */
    public void setData(RealmList<Data> Data) {
        this.Data = Data;
    }
}
