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
        if(generator.equalsIgnoreCase("Dân trí")){
            for (RssFeedItem rssFeedItem : mItem) {
                rssFeedItem.extractDescriptionDantri();
            }
        }else if(generator.equalsIgnoreCase("Kenh14.vn")){
            for (RssFeedItem rssFeedItem : mItem) {
                rssFeedItem.extractDescriptionKenh14();
            }
        }else if(generator.startsWith("VietNamNet")){
            for (RssFeedItem rssFeedItem : mItem) {
                rssFeedItem.extractDescriptionVietnamNet();
            }
        }
        else {
            for (RssFeedItem rssFeedItem : mItem) {
                rssFeedItem.extractDescription();
            }
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
