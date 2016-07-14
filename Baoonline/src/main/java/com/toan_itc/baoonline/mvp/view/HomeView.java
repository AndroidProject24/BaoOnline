package com.toan_itc.baoonline.mvp.view;

import com.toan_itc.baoonline.library.base.view.BaseView;
import com.toan_itc.baoonline.library.base.view.ErrorView;
import com.toan_itc.baoonline.library.base.view.LoadView;
import com.toan_itc.data.model.rss.RssChannel;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface HomeView extends BaseView,LoadView,ErrorView {
    void getRss(RssChannel rssChannel);
}
