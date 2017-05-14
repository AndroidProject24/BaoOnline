package com.toan_itc.baoonline.ui.readnews.mvp;

import com.toan_itc.baoonline.base.presenter.BasePresenter;
import com.toan_itc.baoonline.injector.scope.PerActivity;
import com.toan_itc.data.interactor.UseCase;

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
        //this.mUseCase.execute(new UserListSubscriber());
    }
    /*@RxLogSubscriber
    private final class UserListSubscriber extends DefaultObserver<NewsDetails> {

        @Override
        public void onError(Throwable e) {
            getView().hideLoading();
            getView().showError(new NetworkError(e).getAppErrorMessage());
        }

        @Override
        public void onNext(NewsDetails newsDetails) {
            getView().loadNews(newsDetails);
        }
    }*/
    @Override
    public void detachView() {
        super.detachView();
        mUseCase.unsubscribe();
    }
}
