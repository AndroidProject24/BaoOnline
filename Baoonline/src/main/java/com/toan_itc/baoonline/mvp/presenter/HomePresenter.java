package com.toan_itc.baoonline.mvp.presenter;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.toan_itc.baoonline.library.base.presenter.BasePresenter;
import com.toan_itc.baoonline.library.injector.scope.PerFragment;
import com.toan_itc.baoonline.mvp.view.HomeView;
import com.toan_itc.data.exception.NetworkError;
import com.toan_itc.data.executor.DefaultSubscriber;
import com.toan_itc.data.local.realm.RealmManager;
import com.toan_itc.data.model.rss.RssChannel;
import com.toan_itc.data.usecase.UseCase;

import javax.inject.Inject;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
@PerFragment
public class HomePresenter extends BasePresenter<HomeView> {
    private RealmManager mRealmManager;
    private final UseCase getRssListUseCase;
    //private final UseCase getGetRssListUseCase;
    @Inject
    HomePresenter(UseCase getRssListUseCase){
        this.getRssListUseCase=getRssListUseCase;
       // this.getGetRssListUseCase=getRssListUseCase;
        //this.mDatabaseRealm=databaseRealm;
    }
    public void getRss_Zing(){
        getView().showLoading();
        this.getRssListUseCase.execute(new UserListSubscriber());
    }
    public void getRss_Vnexpress(){
        getView().showLoading();
        //this.getGetRssListUseCase.execute(new UserListSubscriber());
    }
    @RxLogSubscriber
    private final class UserListSubscriber extends DefaultSubscriber<RssChannel> {

        @Override
        public void onCompleted() {
            getView().hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            getView().showLoading();
            getView().showError(new NetworkError(e).getAppErrorMessage());
        }

        @Override
        public void onNext(RssChannel rssChannel) {
            getView().getRss(rssChannel);
        }
    }
    @Override
    public void detachView() {
        super.detachView();
        getRssListUseCase.unsubscribe();
    }
}
