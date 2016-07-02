package com.toan_itc.baoonline.mvp.presenter;

import com.tickaroo.tikxml.TikXml;
import com.toan_it.library.library.data.local.DatabaseRealm;
import com.toan_it.library.library.data.networking.RestData;
import com.toan_it.library.library.mvp.model.rss.RssFeed;
import com.toan_it.library.library.mvp.presenter.BasePresenter;
import com.toan_it.library.library.utils.Logger;
import com.toan_itc.baoonline.mvp.view.HomeView;

import javax.inject.Inject;

import okio.Buffer;
import rx.Subscriber;
import rx.Subscription;

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
        Subscription subscription=mRestData.GetRss("http://vietnamnet.vn/rss/tin-noi-bat.rss")
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hideLoading();
                        getMvpView().showError(e.getMessage(),null);
                    }

                    @Override
                    public void onNext(String string) {
                        try {
                            Logger.e(string);
                            TikXml parse= new TikXml.Builder().exceptionOnUnreadXml(false).build();
                            RssFeed rssFeed=parse.read(new Buffer().writeUtf8(string), RssFeed.class);
                            Logger.e(rssFeed.toString());
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
