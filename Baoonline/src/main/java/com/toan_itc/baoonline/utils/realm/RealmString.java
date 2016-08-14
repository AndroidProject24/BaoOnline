package com.toan_itc.baoonline.utils.realm;

import io.realm.RealmObject;

/**
 * Wrapper over String to support setting a list of Strings in a RealmObject
 */
public class RealmString extends RealmObject {

    private String val;

    public RealmString() {
    }

    public RealmString(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
