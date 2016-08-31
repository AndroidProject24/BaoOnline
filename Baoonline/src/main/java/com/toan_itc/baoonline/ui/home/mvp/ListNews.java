package com.toan_itc.baoonline.ui.home.mvp;

import com.toan_itc.baoonline.library.base.view.BaseView;
import com.toan_itc.baoonline.library.base.view.EmptyView;
import com.toan_itc.baoonline.library.base.view.ErrorView;
import com.toan_itc.baoonline.library.base.view.LoadView;
import com.toan_itc.data.model.rss.RssChannel;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface ListNews extends BaseView,LoadView,ErrorView,EmptyView {
    void getRss(RssChannel rssChannel);
}