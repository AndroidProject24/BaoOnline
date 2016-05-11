package com.toan_itc.baoonline.mvp.model.rss;
/**
 * Created by subodhnijsure on 5/4/16.
 */

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

@Xml(name = "channel")
public class RssChannel{
    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public void setItem(List<RssFeedItem> item) {
        mItem = item;
    }

    @PropertyElement
    private String generator;

    public List<RssFeedItem> getItem() {
        return mItem;
    }

    public String getGenerator() {
        return generator;
    }

    @Element(name = "item")
    List<RssFeedItem> mItem;
    @Override
    public String toString() {
        return "channel{" +
                "mGenerator='" + generator + '\'' +
                ", mItem=" + mItem +
                '}';
    }
}
