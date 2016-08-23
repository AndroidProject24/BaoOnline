package com.toan_itc.baoonline.ui.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.base.BaseFragment;
import com.toan_itc.baoonline.library.injector.module.FragmentModule;
import com.toan_itc.baoonline.library.injector.scope.HasComponent;
import com.toan_itc.baoonline.listener.OnItemClickListener;
import com.toan_itc.baoonline.navigation.Navigator;
import com.toan_itc.baoonline.ui.details.activity.DetailsActivity;
import com.toan_itc.baoonline.ui.home.adapter.ListnewsAdapter;
import com.toan_itc.baoonline.ui.home.di.DaggerListRssComponent;
import com.toan_itc.baoonline.ui.home.di.ListRssComponent;
import com.toan_itc.baoonline.ui.home.di.ListRssModule;
import com.toan_itc.baoonline.ui.home.mvp.HomePresenter;
import com.toan_itc.baoonline.ui.home.mvp.HomeView;
import com.toan_itc.data.libs.image.ImageLoaderListener;
import com.toan_itc.data.model.rss.RssChannel;
import com.toan_itc.data.model.rss.RssFeedItem;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public class ListNewsFragment extends BaseFragment implements HasComponent<ListRssComponent>,HomeView,OnItemClickListener{
    @Inject
    HomePresenter mHomePresenter;
    @Inject
    ImageLoaderListener mImageLoaderListener;
    @Inject
    Provider<Navigator> navigator;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ListRssComponent mListRssComponent;
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public ListNewsFragment() {
        setRetainInstance(true);
    }
    public static ListNewsFragment newInstance(String linkUrl) {
        ListNewsFragment fragment = new ListNewsFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, linkUrl);
        fragment.setArguments(bdl);
        return fragment;
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.listnews_fragment;
    }

    @Override
    protected void injectDependencies() {
        getComponent().inject(this);
    }

    @Override
    protected void initData() {
        mHomePresenter.getRss_Zing();
    }

    @Override
    protected View getLoadingTargetView() {
        return ButterKnife.findById(getActivity(),R.id.recyclerview);
    }

    @Override
    protected void initViews() {
        mHomePresenter.attachView(this);
    }

    @Override
    public void getRss(RssChannel rssChannel) {
        ListnewsAdapter listnewsAdapter =new ListnewsAdapter(getContext(),rssChannel.getItem(),mImageLoaderListener,this);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setAdapter(listnewsAdapter);
       // Logger.e(rssChannel.toString());
    }
    @Override
    public void onItemClick(RssFeedItem rssFeedItem) {
        navigator.get().startActivity(DetailsActivity.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mListRssComponent=null;
        mHomePresenter.detachView();
    }

    @Override
    public ListRssComponent getComponent() {
        if(mListRssComponent == null&&getArguments().getString(EXTRA_MESSAGE)!=null) {
            mListRssComponent = DaggerListRssComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .fragmentModule(new FragmentModule(this))
                    .listRssModule(new ListRssModule(getArguments().getString(EXTRA_MESSAGE)))
                    .build();
        }
        return mListRssComponent;
    }
}
