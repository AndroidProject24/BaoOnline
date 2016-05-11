package com.toan_itc.baoonline.mvp.view;

import com.toan_itc.baoonline.mvp.model.rss.RssFeed;

import java.util.List;

/**
 * Created by Toan.IT on 5/11/16.
 */
public interface HomeView extends BaseView{
    void getRss(List<RssFeed> rssFeedList);

}
