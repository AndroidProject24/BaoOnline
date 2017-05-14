package com.toan_itc.baoonline.ui.home.mvp;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.toan_itc.baoonline.base.presenter.BasePresenter;
import com.toan_itc.baoonline.injector.scope.PerFragment;
import com.toan_itc.baoonline.ui.home.contract.HomeContract;
import com.toan_itc.data.exception.NetworkError;
import com.toan_itc.data.interactor.DefaultObserver;
import com.toan_itc.data.interactor.UseCase;
import com.toan_itc.data.model.rssrealm.RealmFeedItem;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
@PerFragment
public class ListNewsPresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter{
    private final UseCase mUseCase;
    @Inject
    ListNewsPresenter(UseCase useCase){
        this.mUseCase=useCase;
    }

    @Override
    public void getRss_Zing() {
        getView().showLoading();
        this.mUseCase.execute(new UserListSubscriber());
    }

    @RxLogSubscriber
    private class UserListSubscriber extends DefaultObserver<List<RealmFeedItem>> {

        @Override
        public void onCompleted() {
            getView().hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            getView().hideLoading();
            getView().showError(new NetworkError(e).getAppErrorMessage());
        }

        @Override
        public void onNext(List<RealmFeedItem> listData) {
            if(listData.size()>0)
                getView().getRss(listData);
            else
                getView().showEmptyView("");
        }
    }
    @Override
    public void detachView() {
        super.detachView();
        mUseCase.unsubscribe();
    }
}
