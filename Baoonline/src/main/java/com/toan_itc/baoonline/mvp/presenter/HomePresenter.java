package com.toan_itc.baoonline.mvp.presenter;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.toan_itc.baoonline.library.base.presenter.BasePresenter;
import com.toan_itc.baoonline.library.injector.scope.ActivityScope;
import com.toan_itc.baoonline.mvp.view.HomeView;
import com.toan_itc.data.exception.NetworkError;
import com.toan_itc.data.local.DatabaseRealm;
import com.toan_itc.data.model.rss.RssChannel;
import com.toan_itc.data.executor.DefaultSubscriber;
import com.toan_itc.data.usecase.UseCase;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
@ActivityScope
public class HomePresenter extends BasePresenter<HomeView> {
    private DatabaseRealm mDatabaseRealm;
    private final UseCase getRssListUseCase;
    @Inject
    HomePresenter(@Named("userList") UseCase getRssListUseCase, DatabaseRealm databaseRealm){
        this.getRssListUseCase=getRssListUseCase;
        this.mDatabaseRealm=databaseRealm;
    }
    public void getRss_Zing(){
        getView().hideError();
        getView().showLoading();
        this.getRssListUseCase.execute(new UserListSubscriber());
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
            getView().showError(new NetworkError(e).getAppErrorMessage(),null);
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
