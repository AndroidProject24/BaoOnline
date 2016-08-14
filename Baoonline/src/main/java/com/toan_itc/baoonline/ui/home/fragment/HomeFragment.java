package com.toan_itc.baoonline.ui.home.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.base.BaseFragment;
import com.toan_itc.baoonline.library.injector.module.FragmentModule;
import com.toan_itc.baoonline.library.injector.scope.HasComponent;
import com.toan_itc.baoonline.library.libs.image.ImageLoaderListener;
import com.toan_itc.baoonline.listener.OnItemClickListener;
import com.toan_itc.baoonline.navigation.Navigator;
import com.toan_itc.baoonline.ui.details.activity.DetailsActivity;
import com.toan_itc.baoonline.ui.home.adapter.HomeAdapter;
import com.toan_itc.baoonline.ui.home.di.DaggerListRssComponent;
import com.toan_itc.baoonline.ui.home.di.ListRssComponent;
import com.toan_itc.baoonline.ui.home.di.ListRssModule;
import com.toan_itc.baoonline.ui.home.mvp.HomePresenter;
import com.toan_itc.baoonline.ui.home.mvp.HomeView;
import com.toan_itc.data.model.rss.RssChannel;
import com.toan_itc.data.model.rss.RssFeedItem;
import com.toan_itc.data.utils.Constants;
import com.toan_itc.data.utils.logger.Logger;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public class HomeFragment extends BaseFragment implements HasComponent<ListRssComponent>,HomeView,OnItemClickListener{
    @Inject
    HomePresenter mHomePresenter;
    @Inject
    ImageLoaderListener mImageLoaderListener;
    @Inject
    Provider<Navigator> navigator;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private Context mContext;
    private ListRssComponent mListRssComponent;
    public HomeFragment() {
        setRetainInstance(true);
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
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
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
        HomeAdapter homeAdapter=new HomeAdapter(mContext,rssChannel.getItem(),mImageLoaderListener,this);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(homeAdapter);
        Logger.e(rssChannel.toString());
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
        if(mListRssComponent == null) {
            mListRssComponent = DaggerListRssComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .fragmentModule(new FragmentModule(this))
                    .listRssModule(new ListRssModule(Constants.url[1]))
                    .build();
        }
        return mListRssComponent;
    }
}
