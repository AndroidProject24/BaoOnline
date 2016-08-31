package com.toan_itc.data.local.realm.entities;

import com.toan_itc.data.model.rss.RssFeedItem;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Toan.IT
 * Date: 14/07/2016
 */
@Singleton
public class RssDiskRealmMapper implements Mapper<RssFeedItem, RssDiskRealm>{

    @Inject
    public RssDiskRealmMapper(){
        //
    }

    @Override
    public RssDiskRealm modelToData(RssFeedItem model) {
        if (model == null){
            return null;
        }
        RssDiskRealm rssDiskRealm = new RssDiskRealm();
        rssDiskRealm.setImage(model.getImage());
        rssDiskRealm.setDescription(model.getDescription());
       // rssDiskRealm.setPubDate(model.getPubDate());
        rssDiskRealm.setLink(model.getLink());
        return rssDiskRealm;
    }

    @Override
    public RssFeedItem dataToModel(RssDiskRealm data) {
        if (data == null){
            return null;
        }

        RssFeedItem names = new RssFeedItem();
      /*  names.setId(data.getId());
        names.setName(data.getName());
        names.setDescription(data.getDescription());*/

        return names;
    }

}
