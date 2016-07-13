package com.toan_itc.baoonline.mvp.view;

import com.toan_itc.baoonline.library.basemvp.BaseView;
import com.toan_itc.data.model.rss.RssChannel;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface HomeView extends BaseView {
    void getRss(RssChannel rssChannel);

}
