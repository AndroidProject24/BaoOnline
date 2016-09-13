package com.toan_itc.data.model.rssrealm;
/**
 * Created by Toan.IT
 * Date: 29/06/2016
 */

import io.realm.RealmList;
import io.realm.RealmObject;

public class RealmChannel extends RealmObject{

    public RealmList<RealmFeedItem> getItem() {
        return mItem;
    }

    public void setItem(RealmList<RealmFeedItem> item) {
        mItem = item;
    }

    private RealmList<RealmFeedItem> mItem;

}
