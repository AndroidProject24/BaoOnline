package com.toan_itc.baoonline.ui.readnews.mvp;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.toan_itc.baoonline.library.base.presenter.BasePresenter;
import com.toan_itc.baoonline.library.injector.scope.PerActivity;
import com.toan_itc.data.exception.NetworkError;
import com.toan_itc.data.executor.DefaultSubscriber;
import com.toan_itc.data.model.newdetails.NewsDetails;
import com.toan_itc.data.usecase.UseCase;

import javax.inject.Inject;

/**
 * Created by Toan.IT
 * Date: 31/08/2016
 */
@PerActivity
public class ReadNewsPresenter extends BasePresenter<ReadNews> {
    private final UseCase mUseCase;
    @Inject
    ReadNewsPresenter(UseCase useCase){
        this.mUseCase=useCase;
    }
    public void getContent_News(){
        getView().showLoading();
        this.mUseCase.execute(new UserListSubscriber());
    }
    @RxLogSubscriber
    private final class UserListSubscriber extends DefaultSubscriber<NewsDetails> {

        @Override
        public void onError(Throwable e) {
            getView().hideLoading();
            getView().showError(new NetworkError(e).getAppErrorMessage());
        }

        @Override
        public void onNext(NewsDetails newsDetails) {
            getView().loadNews(newsDetails);
        }
    }
    @Override
    public void detachView() {
        super.detachView();
        mUseCase.unsubscribe();
    }
}
