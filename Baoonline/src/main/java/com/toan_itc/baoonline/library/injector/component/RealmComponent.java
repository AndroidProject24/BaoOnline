package com.toan_itc.baoonline.library.injector.component;

import com.toan_itc.data.local.DatabaseRealm;
import com.toan_itc.data.local.PreferencesHelper;

/**
 * Created by Toan.IT
 * Date: 14/07/2016
 */

public interface RealmComponent {

//    NamesRepository namesRepository();


    PreferencesHelper mPreferencesHelper();

    DatabaseRealm mDatabaseRealm();
}
