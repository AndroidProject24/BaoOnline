package com.toan_itc.baoonline.ui.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.base.BaseFragment;
import com.toan_itc.baoonline.library.injector.module.FragmentModule;
import com.toan_itc.baoonline.library.injector.scope.HasComponent;
import com.toan_itc.baoonline.listener.OnItemClickListener;
import com.toan_itc.baoonline.navigation.Navigator;
import com.toan_itc.baoonline.ui.home.adapter.ListnewsAdapter;
import com.toan_itc.baoonline.ui.home.di.DaggerListNewsComponent;
import com.toan_itc.baoonline.ui.home.di.ListNewsComponent;
import com.toan_itc.baoonline.ui.home.di.ListNewsModule;
import com.toan_itc.baoonline.ui.home.mvp.ListNews;
import com.toan_itc.baoonline.ui.home.mvp.ListNewsPresenter;
import com.toan_itc.baoonline.ui.readnews.activity.ReadNewsActivity;
import com.toan_itc.data.libs.image.ImageLoaderListener;
import com.toan_itc.data.libs.view.StateLayout;
import com.toan_itc.data.model.rss.RssChannel;
import com.toan_itc.data.model.rss.RssFeedItem;
import com.toan_itc.data.utils.CommonUtils;
import com.toan_itc.data.utils.Constants;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public class ListNewsFragment extends BaseFragment implements HasComponent<ListNewsComponent>,ListNews,OnItemClickListener{
    @Inject
    ListNewsPresenter mListNewsPresenter;
    @Inject
    ImageLoaderListener mImageLoaderListener;
    @Inject
    Provider<Navigator> navigator;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ListNewsComponent mListNewsComponent;

    public ListNewsFragment() {
        setRetainInstance(true);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.list_news_fragment;
    }

    @Override
    protected void injectDependencies() {
        getComponent().inject(this);
    }

    @Override
    protected void initData() {
        mListNewsPresenter.getRss_Zing();
    }

    @Override
    protected StateLayout getLoadingTargetView() {
        return ButterKnife.findById(getActivity(),R.id.stateLayout);
    }

    @Override
    protected void initViews() {
        mListNewsPresenter.attachView(this);
    }

    @Override
    public void getRss(RssChannel rssChannel) {
        ListnewsAdapter listnewsAdapter =new ListnewsAdapter(getActivity(),rssChannel.getItem(),mImageLoaderListener,this);
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerview.setAdapter(listnewsAdapter);
    }
    @Override
    public void onItemClick(RssFeedItem rssFeedItem) {
        Bundle bundle = new Bundle();
        String link;
        if(!CommonUtils.isEmpty(rssFeedItem.getLink())){
            link=rssFeedItem.getLink();
        }else if(!CommonUtils.isEmpty(rssFeedItem.getArticleLink())){
            link=rssFeedItem.getArticleLink();
        }else{
            link=null;
        }
        bundle.putString(Constants.BUNLDE,link);
        bundle.putString(Constants.NEWS_TITLE,rssFeedItem.getTitle());
        bundle.putString(Constants.NEWS_PUBDATE,rssFeedItem.getPubDate());
        navigator.get().startActivity(ReadNewsActivity.class,bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mListNewsPresenter.detachView();
    }

    @Override
    public ListNewsComponent getComponent() {
        if(mListNewsComponent == null&&getArguments().getString(Constants.BUNLDE)!=null) {
            mListNewsComponent = DaggerListNewsComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .fragmentModule(new FragmentModule(this))
                    .listNewsModule(new ListNewsModule(getArguments().getString(Constants.BUNLDE)))
                    .build();
        }else{
            showEmptyView("Không tìm thấy Rss!");
        }
        return mListNewsComponent;
    }
}
