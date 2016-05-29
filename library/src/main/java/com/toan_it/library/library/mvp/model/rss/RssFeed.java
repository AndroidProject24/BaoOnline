package com.toan_it.library.library.mvp.model.rss;
/**
 * Created by subodhnijsure on 5/4/16.
 */

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;


@Xml(name = "rss")
public class RssFeed{
    @Override
    public String toString() {
        return "RssFeed{" +
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
