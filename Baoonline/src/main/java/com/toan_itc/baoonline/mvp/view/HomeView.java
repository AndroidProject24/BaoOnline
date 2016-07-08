package com.toan_itc.baoonline.mvp.view;

import com.toan_itc.baoonline.library.mvp.model.rss.RssFeed;
import com.toan_itc.baoonline.library.mvp.view.BaseView;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface HomeView extends BaseView {
    void getRss(RssFeed rssFeed);

}
