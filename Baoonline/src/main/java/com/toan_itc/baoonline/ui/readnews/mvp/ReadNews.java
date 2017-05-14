package com.toan_itc.baoonline.ui.readnews.mvp;

import com.toan_itc.baoonline.base.view.BaseView;
import com.toan_itc.baoonline.base.view.EmptyView;
import com.toan_itc.baoonline.base.view.ErrorView;
import com.toan_itc.baoonline.base.view.LoadView;
import com.toan_itc.data.model.newdetails.NewsDetails;

/**
 * Created by Toan.IT
 * Date: 31/08/2016
 */
public interface ReadNews extends BaseView,LoadView,ErrorView,EmptyView {
    void loadNews(NewsDetails newsDetails);
}
