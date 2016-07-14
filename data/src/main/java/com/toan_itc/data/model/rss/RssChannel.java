package com.toan_itc.data.model.rss;
/**
 * Created by Toan.IT
 * Date: 29/06/2016
 */

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

@Xml(name = "channel")
public class RssChannel{

    @PropertyElement
    private String generator;

    @Element(name = "item")
    List<RssFeedItem> mItem;

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public void setItem(List<RssFeedItem> item) {
        mItem = item;
    }

    public List<RssFeedItem> getItem() {
        for (RssFeedItem rssFeedItem : mItem) {
            rssFeedItem.extractDescription();
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
