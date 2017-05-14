package com.toan_itc.baoonline.ui.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.base.BaseFragment;
import com.toan_itc.baoonline.injector.module.FragmentModule;
import com.toan_itc.baoonline.injector.scope.HasComponent;
import com.toan_itc.baoonline.interfaces.OnItemClickListener;
import com.toan_itc.baoonline.navigation.FragmentNavigator;
import com.toan_itc.baoonline.ui.home.adapter.ListnewsAdapter;
import com.toan_itc.baoonline.ui.home.contract.HomeContract;
import com.toan_itc.baoonline.ui.home.di.DaggerListNewsComponent;
import com.toan_itc.baoonline.ui.home.di.ListNewsComponent;
import com.toan_itc.baoonline.ui.home.di.ListNewsModule;
import com.toan_itc.baoonline.ui.home.mvp.ListNewsPresenter;
import com.toan_itc.data.config.Constants;
import com.toan_itc.data.libs.image.ImageLoaderListener;
import com.toan_itc.data.libs.view.StateLayout;
import com.toan_itc.data.model.rssrealm.RealmFeedItem;
import com.toan_itc.data.utils.Preconditions;

import com.toan_itc.data.utils.logger.Logger;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public class ListNewsViewFragment extends BaseFragment<ListNewsPresenter> implements HasComponent<ListNewsComponent>,HomeContract.View,OnItemClickListener{
    @Inject
    ImageLoaderListener mImageLoaderListener;
    @Inject
    FragmentNavigator mFragmentNavigator;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ListNewsComponent mListNewsComponent;

    public ListNewsViewFragment() {
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
        mPresenter.getRss_Zing();
    }

    @Override
    protected StateLayout getLoadingTargetView() {
        return ButterKnife.findById(getActivity(),R.id.stateLayout);
    }

    @Override
    protected void initViews() {

    }

    @Override
    public void getRss(List<RealmFeedItem> realmFeedItemList) {
        Logger.e("getRss="+realmFeedItemList.toString());
        Preconditions.checkState(recyclerview != null, "recyclerview should not be null.");
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerview.setAdapter(new ListnewsAdapter(getContext(),Preconditions.checkNotNull(realmFeedItemList, "realmFeedItemList cannot be null."),mImageLoaderListener,this));
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

    @Override
    public void onItemClick(RealmFeedItem data) {
        Bundle bundle = new Bundle();
        String link;
        if(!Preconditions.isEmpty(data.getLink())){
            link=data.getLink();
        }/*else if(!CommonUtils.isEmpty(rssFeedItem.getLink())){
            link=rssFeedItem.getLink();
        }*/else{
            link=null;
        }
        bundle.putString(Constants.BUNLDE,link);
        bundle.putString(Constants.NEWS_TITLE,data.getTitle());
        bundle.putString(Constants.NEWS_PUBDATE,data.getPubDate());
        //mFragmentNavigator.startActivity(ReadNewsActivity.class,bundle);
        //new   navigator.get().startActivity(ReadNewsActivity.class,bundle);//TODO : new
        //
       /* if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            final android.support.v4.util.Pair<View, String>[] pairs = Help.createSafeTransitionParticipants
                    ((Activity) mContext, false,new android.support.v4.util.Pair<>(holder.imageView, mContext.getString(R.string.transition_shot)),
                            new android.support.v4.util.Pair<>(holder.linearLayout, mContext.getString(R.string.transition_shot_background)));
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, pairs);
            mContext.startActivity(intent, options.toBundle());
        }else {

            mContext.startActivity(intent);

        }*/
    }
}
