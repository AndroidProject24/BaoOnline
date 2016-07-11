package com.toan_itc.baoonline.ui.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.listener.OnItemClickListener;
import com.toan_itc.baoonline.library.basefragment.BaseFragment;
import com.toan_itc.baoonline.library.libs.image.ImageLoaderListener;
import com.toan_itc.baoonline.mvp.model.rss.RssFeed;
import com.toan_itc.baoonline.mvp.model.rss.RssFeedItem;
import com.toan_itc.baoonline.library.utils.Logger;
import com.toan_itc.baoonline.mvp.presenter.HomePresenter;
import com.toan_itc.baoonline.mvp.view.HomeView;
import com.toan_itc.baoonline.ui.adapter.HomeAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public class HomeFragment extends BaseFragment implements HomeView,OnItemClickListener{
    @Inject
    HomePresenter mHomePresenter;
    @Inject
    ImageLoaderListener mImageLoaderListener;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private Context mContext;
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
        getComponent().inject(this);
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
    public void onItemClick(RssFeedItem rssFeedItem) {

    }
}
