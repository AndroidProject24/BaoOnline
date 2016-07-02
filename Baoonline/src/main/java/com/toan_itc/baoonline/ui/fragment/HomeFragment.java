package com.toan_itc.baoonline.ui.fragment;

import android.util.Log;
import android.view.View;

import com.toan_it.library.library.fragment.BaseFragment;
import com.toan_it.library.library.mvp.model.rss.RssFeed;
import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.mvp.presenter.HomePresenter;
import com.toan_itc.baoonline.mvp.view.HomeView;
import com.toan_itc.baoonline.ui.activity.MainActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public class HomeFragment extends BaseFragment implements HomeView{
    @Inject
    HomePresenter mHomePresenter;
    public HomeFragment() {
        // Requires empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void injectDependencies() {
        ((MainActivity)getActivity()).getActivityComponent().inject(this);
        mHomePresenter.attachView(this);
    }

    @Override
    protected void initData() {
        mHomePresenter.getRss_Zing();
    }

    @Override
    protected void initViews() {

    }

    @Override
    public void getRss(List<RssFeed> rssFeedList) {
        Log.wtf("getRss=",rssFeedList.get(0).getChannel().toString());
    }

    @Override
    public void showLoading(String msg) {
        getAVLoadingIndicatorView().setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        getAVLoadingIndicatorView().setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String msg, View.OnClickListener onClickListener) {

    }

    @Override
    public void showEmpty(String msg, View.OnClickListener onClickListener) {

    }

    @Override
    public void showEmpty(String msg, View.OnClickListener onClickListener, int imageId) {

    }

    @Override
    public void showNetError(View.OnClickListener onClickListener) {

    }
}
