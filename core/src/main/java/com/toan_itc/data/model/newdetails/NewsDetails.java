package com.toan_itc.data.model.newdetails;

/**
 * Created by Toan.IT
 * Date: 31/08/2016
 * Email: huynhvantoan.itc@gmail.com
 */

public class NewsDetails {
    private String title;
    private String time;
    private String description;
    private String details;

    public NewsDetails(String title, String time, String description, String details) {
        this.title = title;
        this.time = time;
        this.description = description;
        this.details = details;
    }
    public NewsDetails(){}
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
