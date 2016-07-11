package com.toan_itc.baoonline.mvp.presenter;

import com.tickaroo.tikxml.TikXml;
import com.toan_itc.baoonline.library.basemvp.BasePresenter;
import com.toan_itc.baoonline.mvp.view.HomeView;
import com.toan_itc.data.local.DatabaseRealm;
import com.toan_itc.data.model.rss.RssFeed;
import com.toan_itc.data.net.RestData;
import com.toan_itc.data.net.RestError;
import com.toan_itc.data.utils.Logger;

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
        getMvpView().showLoading(true);
        Subscription subscription=mRestData.GetRss(mDatabaseRealm.getNews(0).getData().get(0).getUrl())
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        Logger.e("doOnCompleted");
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().showLoading(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showLoading(false);
                        getMvpView().showError(new RestError(e).getAppErrorMessage(),null);
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
