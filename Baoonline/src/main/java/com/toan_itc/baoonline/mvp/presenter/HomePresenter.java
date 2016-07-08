package com.toan_itc.baoonline.mvp.presenter;

import com.tickaroo.tikxml.TikXml;
import com.toan_itc.baoonline.library.data.local.DatabaseRealm;
import com.toan_itc.baoonline.library.data.networking.RestData;
import com.toan_itc.baoonline.library.mvp.model.rss.RssFeed;
import com.toan_itc.baoonline.library.mvp.presenter.BasePresenter;
import com.toan_itc.baoonline.library.utils.Logger;
import com.toan_itc.baoonline.mvp.view.HomeView;

import javax.inject.Inject;

import okio.Buffer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class HomePresenter extends BasePresenter<HomeView> {
    private RestData mRestData;
    private DatabaseRealm mDatabaseRealm;
    @Inject
    HomePresenter(RestData restData, DatabaseRealm databaseRealm){
        this.mRestData=restData;
        this.mDatabaseRealm=databaseRealm;
    }
    public void getRss_Zing(){
        getMvpView().showLoading();
        Subscription subscription=mRestData.GetRss("http://www.nguoiduatin.vn/trang-chu.rss")
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        Logger.e("doOnCompleted");
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hideLoading();
                        getMvpView().showError(e.getMessage(),null);
                    }

                    @Override
                    public void onNext(String string) {
                        try {
                            Logger.d(string);
                            TikXml parse= new TikXml.Builder().exceptionOnUnreadXml(false).build();
                            RssFeed rssFeed=parse.read(new Buffer().writeUtf8(string), RssFeed.class);
                            getMvpView().getRss(rssFeed);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
        unsubscribeOnUnbindView(subscription);
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}
