package com.toan_itc.data.local.realm.entities;

import com.toan_itc.data.model.rss.RssChannel;
import com.toan_itc.data.model.rss.RssFeedItem;
import com.toan_itc.data.model.rssrealm.RealmChannel;
import com.toan_itc.data.model.rssrealm.RealmFeedItem;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by Toan.IT
 * Date: 14/07/2016
 */
public class RssDiskRealmMapper implements Mapper<RealmChannel, RssChannel>{

    public RssDiskRealmMapper(){
        //
    }
    @Override
    public RssChannel modelToData(RealmChannel model) {
	    if (model == null){
		    return null;
	    }
	    RssChannel rssChannel = new RssChannel();
	    List<RssFeedItem> list=new ArrayList<>();
	    /*for(RealmFeedItem item: model.getItem()){
		    RssFeedItem realmFeedItem=new RssFeedItem();
		    RssFeedItem.setImage(item.getImage());
		    RssFeedItem.setTitle(item.getTitle());
		    RssFeedItem.setDescription(item.getDescription());
		    RssFeedItem.setPubDate(item.getPubDate());
		    RssFeedItem.setLink(item.getLink());
		    list.add(realmFeedItem);
	    }*/
	    rssChannel.setItem(list);
	    return rssChannel;
    }

    @Override
    public RealmChannel dataToModel(RssChannel data) {
        if (data == null){
            return null;
        }
        RealmChannel rssDiskRealm = new RealmChannel();
        RealmList<RealmFeedItem> list=new RealmList<>();
        for(RssFeedItem item: data.getItem()){
            RealmFeedItem realmFeedItem=new RealmFeedItem();
            realmFeedItem.setImage(item.getImage());
            realmFeedItem.setTitle(item.getTitle());
            realmFeedItem.setDescription(item.getDescription());
            realmFeedItem.setPubDate(item.getPubDate());
	        realmFeedItem.setArticle(item.getArticle());
            realmFeedItem.setLink(item.getLink());
            list.add(realmFeedItem);
        }
        rssDiskRealm.setItem(list);
        return rssDiskRealm;
    }
}
