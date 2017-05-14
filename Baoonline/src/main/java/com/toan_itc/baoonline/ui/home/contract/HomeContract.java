package com.toan_itc.baoonline.ui.home.contract;

import com.toan_itc.baoonline.base.view.BaseView;
import com.toan_itc.baoonline.base.view.EmptyView;
import com.toan_itc.baoonline.base.view.ErrorView;
import com.toan_itc.baoonline.base.view.LoadView;
import com.toan_itc.data.model.rssrealm.RealmFeedItem;

import java.util.List;

/**
 * Toan.IT
 * Created by vantoan on 3/20/17.
 * Email: Huynhvantoan.itc@gmail.com
 */

public interface HomeContract {

  interface View extends BaseView,LoadView,ErrorView,EmptyView {
    void getRss(List<RealmFeedItem> realmFeedItemList);
  }

  interface Presenter {
    void getRss_Zing();
  }
}