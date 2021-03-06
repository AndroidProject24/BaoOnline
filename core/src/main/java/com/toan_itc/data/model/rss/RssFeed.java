package com.toan_itc.data.model.rss;
/**
 * Created by Toan.IT
 * Date: 29/06/2016
 */

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;


@Xml(name = "rss")
public class RssFeed{
    @Override
    public String toString() {
        return "RealmFeed{" +
                "channel=" + channel +
                '}';
    }

    @Element
    private RssChannel channel;
    public RssChannel getChannel() {
        return channel;
    }
    public void setChannel(RssChannel channel) {
        this.channel = channel;
    }
}
