package com.toan_itc.baoonline.ui.details.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.base.BaseFragment;
import com.toan_itc.baoonline.library.libs.image.ImageLoaderListener;
import com.toan_itc.baoonline.listener.OnItemClickListener;
import com.toan_itc.baoonline.mvp.presenter.HomePresenter;
import com.toan_itc.baoonline.mvp.view.HomeView;
import com.toan_itc.baoonline.ui.home.adapter.HomeAdapter;
import com.toan_itc.data.model.rss.RssChannel;
import com.toan_itc.data.model.rss.RssFeedItem;
import com.toan_itc.data.utils.logger.Logger;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public class DetailsRssFragment extends BaseFragment implements HomeView,OnItemClickListener{
    @Inject
    HomePresenter mHomePresenter;
    @Inject
    ImageLoaderListener mImageLoaderListener;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private Context mContext;
    public DetailsRssFragment() {
        setRetainInstance(true);
    }

    public static DetailsRssFragment newInstance() {
        return new DetailsRssFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void injectDependencies() {
       /* getComponent(RssActivityComponent.class)
               .listRssModule(new ListRssModule(Constants.url[0]))
                .inject(this);*/
    }

    @Override
    protected void initData() {
        mHomePresenter.getRss_Zing();
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViews() {
        mHomePresenter.attachView(this);
    }

    @Override
    public void getRss(RssChannel rssChannel) {
        HomeAdapter homeAdapter=new HomeAdapter(mContext,rssChannel.getItem(),mImageLoaderListener,this);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(homeAdapter);
        Logger.e(rssChannel.toString());
    }


    @Override
    public void onItemClick(RssFeedItem rssFeedItem) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHomePresenter.detachView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showNetworkError() {

    }
}
