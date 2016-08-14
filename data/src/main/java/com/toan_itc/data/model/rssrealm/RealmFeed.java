package com.toan_itc.data.model.rssrealm;
/**
 * Created by Toan.IT
 * Date: 29/06/2016
 */

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;


@Xml(name = "rss")
public class RealmFeed {
    @Override
    public String toString() {
        return "RealmFeed{" +
                "channel=" + channel +
                '}';
    }

    @Element
    private RealmChannel channel;
    public RealmChannel getChannel() {
        return channel;
    }
    public void setChannel(RealmChannel channel) {
        this.channel = channel;
    }
}
