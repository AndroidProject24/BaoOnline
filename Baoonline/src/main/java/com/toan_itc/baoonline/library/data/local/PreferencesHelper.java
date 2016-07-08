package com.toan_itc.baoonline.library.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesHelper {
    private static SharedPreferences mPref;
    public static final String PREF_FILE_NAME = "kctbox_pref_file";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_USER_NAME = "PREF_KEY_USER_NAME";
    private static final String PREF_KEY_USER_ID = "PREF_KEY_USER_ID";
    private static final String PREF_KEY_AUTO_LOOP_VIDEOS = "PREF_KEY_AUTO_LOOP_VIDEOS";
    private static final String KEYCODE = "KEYCODE";
    private static final String POSITION = "POSITION";
    private static final String CHECK_CHANGE = "CHECK_CHANGE";
    private static final String REMOTE_ISSHOW = "REMOTE_ISSHOW";
    private static final String ID_SONG_PLAY = "ID_SONG_PLAY";
    private static final String SCREEN_TYPE = "SCREEN_TYPE";
    private static final String LOCATION_DIALOG_WIDTH = "LOCATION_DIALOG_WIDTH";
    private static final String LOCATION_DIALOG_HEIGHT = "LOCATION_DIALOG_HEIGHT";
    private static final String SCREEN_WIDTH = "SCREEN_WIDTH";
    private static final String SCREEN_HEIGHT = "SCREEN_HEIGHT";
    private static final String ITEM_HEIGHT = "ITEM_HEIGHT";
    private static final String ITEM_WIDTH = "ITEM_WIDTH";
    private static final String SONG_MV = "SONG_MV";
    private static final String SONG_BEAT = "SONG_BEAT";
    private static final String SONG_NAME = "SONG_NAME";
    private static final String SONG_TYPE = "SONG_TYPE";
    private static final String SONG_SINGER = "SONG_SINGER";
    private static final String SONG_ID = "SONG_ID";
    @Inject
    public PreferencesHelper(Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }
    //Gson
    public void putAccessToken(String accessToken) {
      //  mPref.edit().putString(PREF_KEY_ACCESS_TOKEN, new Gson().toJson(accessToken)).apply();
    }

    @Nullable
    public String getAccessToken() {
        String token = mPref.getString(PREF_KEY_ACCESS_TOKEN, null);
        if (token != null) {
           // return new Gson().fromJson(token, AccessToken.class);
        }
        return null;
    }


    public void clear() {
        mPref.edit().clear().apply();
    }
    public void putDialogWidth(int width) {
        mPref.edit().putInt(LOCATION_DIALOG_WIDTH, width).apply();
    }
    public int getDialogWidth() {
        return mPref.getInt(LOCATION_DIALOG_WIDTH, 0);
    }
    public void putDialogHeight(int height) {
        mPref.edit().putInt(LOCATION_DIALOG_HEIGHT, height).apply();
    }
    public int getDialogHeight() {
        return mPref.getInt(LOCATION_DIALOG_HEIGHT, 0);
    }
    //Item Home
    //Screen width,height
    public void putItemWidth(int width) {
        mPref.edit().putInt(ITEM_WIDTH, width).apply();
    }
    public int getItemWidth() {
        return mPref.getInt(ITEM_WIDTH, 0);
    }
    public void putItemHeight(int height) {
        mPref.edit().putInt(ITEM_HEIGHT, height).apply();
    }
    public int getItemHeight() {
        return mPref.getInt(ITEM_HEIGHT, 0);
    }
    //Song Intent
    public void putSongId(String id) {
        mPref.edit().putString(SONG_ID, id).apply();
    }
    @NonNull
    public String getSongId() {
        return mPref.getString(SONG_ID,"");
    }
    public void putSongMv(String mv) {
        mPref.edit().putString(SONG_MV, mv).apply();
    }
    @NonNull
    public String getSongMv() {
        return mPref.getString(SONG_MV,"");
    }
    public void putSongBeat(String beat) {
        mPref.edit().putString(SONG_BEAT, beat).apply();
    }
    @NonNull
    public String getSongBeat() {
        return mPref.getString(SONG_BEAT,"");
    }
    public void putSongName(String name) {
        mPref.edit().putString(SONG_NAME, name).apply();
    }
    @NonNull
    public String getSongName() {
        return mPref.getString(SONG_NAME,"");
    }
    public void putSongType(String type) {
        mPref.edit().putString(SONG_TYPE, type).apply();
    }
    @NonNull
    public String getSongType() {
        return mPref.getString(SONG_TYPE,"");
    }
    public void putSongSinger(String type) {
        mPref.edit().putString(SONG_SINGER, type).apply();
    }
    @NonNull
    public String getSongSinger() {
        return mPref.getString(SONG_SINGER,"");
    }
}
