package com.toan_itc.baoonline.ui.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.basefragment.BaseFragment;
import com.toan_itc.baoonline.library.injector.component.UserComponent;
import com.toan_itc.baoonline.library.injector.scope.HasComponent;
import com.toan_itc.baoonline.library.libs.image.ImageLoaderListener;
import com.toan_itc.baoonline.listener.OnItemClickListener;
import com.toan_itc.baoonline.mvp.presenter.HomePresenter;
import com.toan_itc.baoonline.mvp.view.HomeView;
import com.toan_itc.baoonline.ui.adapter.HomeAdapter;
import com.toan_itc.data.model.rss.RssFeed;
import com.toan_itc.data.model.rss.RssFeedItem;
import com.toan_itc.data.utils.Logger;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public class HomeFragment extends BaseFragment implements HomeView,OnItemClickListener, HasComponent<UserComponent> {
    @Inject
    HomePresenter mHomePresenter;
    @Inject
    ImageLoaderListener mImageLoaderListener;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private Context mContext;
    private UserComponent userComponent;
    public HomeFragment() {
        // Requires empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
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
        this.getComponent(UserComponent.class).inject(this);
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
    public void getRss(RssFeed rssFeed) {
        HomeAdapter homeAdapter=new HomeAdapter(mContext,rssFeed.getChannel().getItem(),mImageLoaderListener,this);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(homeAdapter);
        Logger.e(rssFeed.getChannel().toString());
    }

    @Override
    public void showLoading(boolean loading) {
        ShowLoading(loading);
    }

    @Override
    public void showError(String msg, View.OnClickListener onClickListener) {

    }

    @Override
    public void showRetry(boolean isShow) {

    }

    @Override
    public void onItemClick(RssFeedItem rssFeedItem) {

    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }
}
