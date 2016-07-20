package com.toan_itc.baoonline.library.injector.component;

import com.toan_itc.data.local.PreferencesHelper;
import com.toan_itc.data.local.realm.RealmManager;

/**
 * Created by Toan.IT
 * Date: 14/07/2016
 */

public interface RealmComponent {

//    NamesRepository namesRepository();


    PreferencesHelper mPreferencesHelper();

    RealmManager mDatabaseRealm();
}
