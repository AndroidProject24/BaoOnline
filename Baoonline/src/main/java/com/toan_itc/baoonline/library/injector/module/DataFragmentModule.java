package com.toan_itc.baoonline.library.injector.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.toan_itc.baoonline.library.injector.scope.PerFragment;
import com.toan_itc.data.local.PreferencesHelper;
import com.toan_itc.data.local.realm.RealmManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Toan.IT
 * Date: 04/07/2016
 */
@Module
public class DataFragmentModule {

    private Context mContext;

    public DataFragmentModule(Context context) {
        this.mContext = context;
    }

    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @NonNull
    PreferencesHelper mPreferencesHelper() {
        return new PreferencesHelper(mContext);
    }

    @Provides
    @NonNull
    @PerFragment
    RealmManager mRealmManager() {
        return new RealmManager(mContext);
    }

}
