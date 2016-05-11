/*
package com.toan_itc.baoonline.data.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class News {
    public String[] url;
    private String geturl;
    String link_dantri="http://dantri.com.vn/";
    String link_dspl="http://www.doisongphapluat.com/";
    String link_ngoisao="http://ngoisao.net/rss/";
    String link_kenh14="http://kenh14.vn/";
    String link_bongda="http://bongdaplus.vn/rss/";
    String link_gamek="http://gamek.vn/";
    String link_vnexpress="http://vnexpress.net/rss/";
    public List<String> getTitleTinHot_Tab(Context context) {
        List<String> list_title=new ArrayList<>();
        list_title.add("Zing News");
        list_title.add("Việt Nam Net");
        list_title.add("Zing News");
        list_title.add("Zing News");
        return list_title;
    }
    public String getUrlTinHot(int i) {
        url = new String[]{"http://news.zing.vn/rss/trang-chu.rss","http://vietnamnet.vn/rss/tin-noi-bat.rss","http://www.nguoiduatin.vn/trang-chu.rss","http://www.24h.com.vn/upload/rss/tintuctrongngay.rss","https://www.tinhte.vn/rss","http://cand.com.vn/rss/trang-chu/"};
        geturl= url[i];
        return geturl;
    }
    //VnExpress
    public ArrayList getTitleVnExpress_Tab(Context context) {
        ArrayList<String> array_list_title_tab = new ArrayList<>();
        connection = new Connection(context);
        try {
            connection.createDataBase();
            if (connection.checkDataBase()) {
                connection.openDataBase();
                db = connection.getWritableDatabase();
                cursor = db.rawQuery("SELECT title FROM vnexpress", null);
                if (cursor.moveToFirst()) {
                    do {
                        String title=cursor.getString(cursor.getColumnIndex("title"));
                        array_list_title_tab.add(title);
                    }
                    while (cursor.moveToNext());
                    {
                    }
                }
                connection.close();
            }
        } catch (IOException e) {
            Log.w("Error", "Không thể lấy dữ liệu!!!");
        }
        return array_list_title_tab;
    }
    public String getUrlVnExpress(int i) {
        url = new String[]{link_vnexpress+"tin-moi-nhat.rss", link_vnexpress+"thoi-su.rss",link_vnexpress+"the-gioi.rss",link_vnexpress+"kinh-doanh.rss",link_vnexpress+"giai-tri.rss",link_vnexpress+"the-thao.rss",link_vnexpress+"phap-luat.rss",link_vnexpress+"khoa-hoc.rss",link_vnexpress+"doi-song.rss",link_vnexpress+"tam-su.rss",link_vnexpress+"cuoi.rss"};
        geturl= url[i];
        return geturl;
    }
    //Dantri
    public ArrayList getTitleDantri_Tab(Context context) {
        ArrayList<String> array_list_title_tab = new ArrayList<>();
        connection = new Connection(context);
        try {
            connection.createDataBase();
            if (connection.checkDataBase()) {
                connection.openDataBase();
                db = connection.getWritableDatabase();
                cursor = db.rawQuery("SELECT title FROM dantri", null);
                if (cursor.moveToFirst()) {
                    do {
                        String title=cursor.getString(cursor.getColumnIndex("title"));
                        array_list_title_tab.add(title);
                    }
                    while (cursor.moveToNext());
                    {
                    }
                }
                connection.close();
            }
        } catch (IOException e) {
            Log.w("Error", "Không thể lấy dữ liệu!!!");
        }
        return array_list_title_tab;
    }
    public String getUrlDantri(int i) {
        url = new String[]{link_dantri+"xa-hoi.rss", link_dantri+"tinhyeu-gioitinh.rss",link_dantri+"suc-manh-tri-thuc.rss",link_dantri+"Thegioi.rss",link_dantri+"The-Thao.rss",link_dantri+"giaoduc-khuyenhoc.rss",link_dantri+"kinhdoanh.rss",link_dantri+"van-hoa.rss",link_dantri+"giaitri.rss",link_dantri+"skphapluat.rss"};
        geturl= url[i];
        return geturl;
    }
//Doi song va phap luat
    public ArrayList getTitleDoisongphapluat_Tab(Context context) {
        ArrayList<String> array_list_title_tab = new ArrayList<>();
        connection = new Connection(context);
        try {
            connection.createDataBase();
            if (connection.checkDataBase()) {
                connection.openDataBase();
                db = connection.getWritableDatabase();
                cursor = db.rawQuery("SELECT title FROM doisongphapluat", null);
                if (cursor.moveToFirst()) {
                    do {
                        String title=cursor.getString(cursor.getColumnIndex("title"));
                        array_list_title_tab.add(title);
                    }
                    while (cursor.moveToNext());
                    {
                    }
                }
                connection.close();
            }
        } catch (IOException e) {
            Log.w("Error", "Không thể lấy dữ liệu!!!");
        }
        return array_list_title_tab;
    }
    public String getUrlDoisongphapluat(int i) {
        url = new String[]{link_dspl+"trang-chu.rss", link_dspl+"rss/tin-tuc.rss",link_dspl+"rss/phap-luat.rss",link_dspl+"rss/doi-song.rss",link_dspl+"rss/the-gioi.rss",link_dspl+"rss/kinh-doanh.rss",link_dspl+"rss/giai-tri.rss",link_dspl+"rss/giao-duc.rss",link_dspl+"rss/the-thao.rss",link_dspl+"rss/an-ninh-tv.rss",link_dspl+"rss/cong-nghe.rss",link_dspl+"rss/thoi-trang.rss",link_dspl+"rss/can-biet.rss",link_dspl+"rss/tu-thien.rss",link_dspl+"rss/video.rss"};
        geturl= url[i];
        return geturl;
    }
    //Ngoi Sao
    public ArrayList getTitleNgoiSao_Tab(Context context) {
        ArrayList<String> array_list_title_tab = new ArrayList<>();
        connection = new Connection(context);
        try {
            connection.createDataBase();
            if (connection.checkDataBase()) {
                connection.openDataBase();
                db = connection.getWritableDatabase();
                cursor = db.rawQuery("SELECT title FROM ngoisao", null);
                if (cursor.moveToFirst()) {
                    do {
                        String title=cursor.getString(cursor.getColumnIndex("title"));
                        array_list_title_tab.add(title);
                    }
                    while (cursor.moveToNext());
                    {
                    }
                }
                connection.close();
            }
        } catch (IOException e) {
            Log.w("Error", "Không thể lấy dữ liệu!!!");
        }
        return array_list_title_tab;
    }
    public String getUrlNgoiSao(int i) {
        url = new String[]{link_ngoisao+"hau-truong.rss", link_ngoisao+"showbiz-viet.rss",link_ngoisao+"chau-a.rss",link_ngoisao+"hollywood.rss",link_ngoisao+"24h.rss",link_ngoisao+"the-thao.rss",link_ngoisao+"thoi-trang.rss",link_ngoisao+"lam-dep.rss",link_ngoisao+"khoe-dep.rss",link_ngoisao+"phong-cach.rss",link_ngoisao+"hinh-su.rss",link_ngoisao+"cam-nang.rss",link_ngoisao+"cuoi-hoi.rss",link_ngoisao+"anh-cuoi.rss",link_ngoisao+"chuyen-la.rss",link_ngoisao+"thuong-truong.rss",link_ngoisao+"dan-choi.rss",link_ngoisao+"an-choi.rss",link_ngoisao+"tam-tinh.rss",link_ngoisao+"trac-nghiem.rss",link_ngoisao+"goc-doc-gia.rss",link_ngoisao+"thu-gian.rss",link_ngoisao+"cuoi.rss"};
        geturl= url[i];
        return geturl;
    }
//Kenh14
    public ArrayList getTitleKenh14_Tab(Context context) {
        ArrayList<String> array_list_title_tab = new ArrayList<>();
        connection = new Connection(context);
        try {
            connection.createDataBase();
            if (connection.checkDataBase()) {
                connection.openDataBase();
                db = connection.getWritableDatabase();
                cursor = db.rawQuery("SELECT title FROM kenh14", null);
                if (cursor.moveToFirst()) {
                    do {
                        String title=cursor.getString(cursor.getColumnIndex("title"));
                        array_list_title_tab.add(title);
                    }
                    while (cursor.moveToNext());
                    {
                    }
                }
                connection.close();
            }
        } catch (IOException e) {
            Log.w("Error", "Không thể lấy dữ liệu!!!");
        }
        return array_list_title_tab;
    }
    public String getUrlKenh14(int i) {
        url = new String[]{link_kenh14+"home.rss", link_kenh14+"cine.rss",link_kenh14+"star.rss",link_kenh14+"suc-khoe-gioi-tinh.rss",link_kenh14+"fashion.rss",link_kenh14+"2-tek.rss",link_kenh14+"kham-pha.rss",link_kenh14+"cool.rss",link_kenh14+"goc-trai-tim.rss",link_kenh14+"hoc-duong.rss"};
        geturl= url[i];
        return geturl;
    }
//Bongdaplus
    public ArrayList getTitleBongda_Tab(Context context) {
        ArrayList<String> array_list_title_tab = new ArrayList<>();
        connection = new Connection(context);
        try {
            connection.createDataBase();
            if (connection.checkDataBase()) {
                connection.openDataBase();
                db = connection.getWritableDatabase();
                cursor = db.rawQuery("SELECT title FROM bongda", null);
                if (cursor.moveToFirst()) {
                    do {
                        String title=cursor.getString(cursor.getColumnIndex("title"));
                        array_list_title_tab.add(title);
                    }
                    while (cursor.moveToNext());
                    {
                    }
                }
                connection.close();
            }
        } catch (IOException e) {
            Log.w("Error", "Không thể lấy dữ liệu!!!");
        }
        return array_list_title_tab;
    }
    public String getUrlBongda(int i) {
        url = new String[]{link_bongda+"trang-chu.rss", link_bongda+"viet-nam/2.rss",link_bongda+"champions-league/36.rss",link_bongda+"anh/13.rss",link_bongda+"tay-ban-nha/18.rss",link_bongda+"italia/21.rss",link_bongda+"duc/24.rss",link_bongda+"phap/27.rss",link_bongda+"the-gioi/30.rss",link_bongda+"chuyen-nhuong/58.rss"};
        geturl= url[i];
        return geturl;
    }
    //Cong nghe
    public ArrayList getTitleCongnghe_Tab(Context context) {
        ArrayList<String> array_list_title_tab = new ArrayList<>();
        connection = new Connection(context);
        try {
            connection.createDataBase();
            if (connection.checkDataBase()) {
                connection.openDataBase();
                db = connection.getWritableDatabase();
                cursor = db.rawQuery("SELECT title FROM congnghe", null);
                if (cursor.moveToFirst()) {
                    do {
                        String title=cursor.getString(cursor.getColumnIndex("title"));
                        array_list_title_tab.add(title);
                    }
                    while (cursor.moveToNext());
                    {
                    }
                }
                connection.close();
            }
        } catch (IOException e) {
            Log.w("Error", "Không thể lấy dữ liệu!!!");
        }
        return array_list_title_tab;
    }
    public String getUrlCongnghe(int i) {
        url = new String[]{"http://vnexpress.net/rss/so-hoa.rss","http://vnreview.vn/feed/-/rss/home","http://www.pcworld.com.vn/articles.rss","http://www.techz.vn/rss/trang-chu.rss"};
        geturl= url[i];
        return geturl;
    }
//GameK
    public ArrayList getTitleGameK_Tab(Context context) {
        ArrayList<String> array_list_title_tab = new ArrayList<>();
        connection = new Connection(context);
        try {
            connection.createDataBase();
            if (connection.checkDataBase()) {
                connection.openDataBase();
                db = connection.getWritableDatabase();
                cursor = db.rawQuery("SELECT title FROM gamek", null);
                if (cursor.moveToFirst()) {
                    do {
                        String title=cursor.getString(cursor.getColumnIndex("title"));
                        array_list_title_tab.add(title);
                    }
                    while (cursor.moveToNext());
                    {
                    }
                }
                connection.close();
            }
        } catch (IOException e) {
            Log.w("Error", "Không thể lấy dữ liệu!!!");
        }
        return array_list_title_tab;
    }
    public String getUrlGameK(int i) {
        url = new String[]{link_gamek+"trang-chu.rss", link_gamek+"game-online.rss",link_gamek+"thi-truong.rss",link_gamek+"pc-console.rss",link_gamek+"esport.rss",link_gamek+"mobile-social.rss",link_gamek+"gaming-gear.rss",link_gamek+"manga-film.rss"};
        geturl= url[i];
        return geturl;
    }
}*/
