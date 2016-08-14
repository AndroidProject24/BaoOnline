package com.toan_itc.data.model.rssrealm;
/**
 * Created by Toan.IT
 * Date: 29/06/2016
 */

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

@Xml(name = "channel")
public class RealmChannel {

    @PropertyElement
    private String generator;

    @Element(name = "item")
    List<RealmFeedItem> mItem;

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public void setItem(List<RealmFeedItem> item) {
        mItem = item;
    }

    public List<RealmFeedItem> getItem() {
        for (RealmFeedItem realmFeedItem : mItem) {
            realmFeedItem.extractDescription();
        }
        return mItem;
    }

    public String getGenerator() {
        return generator;
    }
    @Override
    public String toString() {
        return "channel{" +
                "mGenerator='" + generator + '\'' +
                ", mItem=" + mItem +
                '}';
    }
}
