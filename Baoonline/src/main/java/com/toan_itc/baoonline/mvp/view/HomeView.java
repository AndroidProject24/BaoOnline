package com.toan_itc.baoonline.mvp.view;

import com.toan_it.library.library.mvp.model.rss.RssFeed;
import com.toan_it.library.library.mvp.view.BaseView;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface HomeView extends BaseView {
    void getRss(List<RssFeed> rssFeedList);

}
