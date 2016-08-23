package com.toan_itc.baoonline.ui.home.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.base.BaseFragment;
import com.toan_itc.baoonline.library.injector.module.DataFragmentModule;
import com.toan_itc.baoonline.library.injector.scope.HasComponent;
import com.toan_itc.baoonline.ui.home.adapter.TabPageAdapter;
import com.toan_itc.baoonline.ui.home.di.DaggerHomeComponent;
import com.toan_itc.baoonline.ui.home.di.HomeComponent;
import com.toan_itc.data.local.realm.RealmManager;
import com.toan_itc.data.model.news.Tinhot;
import com.toan_itc.data.performance.PerformanceLog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public class HomeFragment extends BaseFragment implements HasComponent<HomeComponent> {
    @Inject
    RealmManager mRealmManager;
    private HomeComponent mHomeComponent;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    public HomeFragment() {
        setRetainInstance(true);
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.home_fragment;
    }

    @Override
    protected void injectDependencies() {
        getComponent().inject(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected View getLoadingTargetView() {
        return mViewPager;
    }

    @Override
    protected void initViews() {
        PerformanceLog.stop("stop:initViews");
        PerformanceLog.start("start:initViews");
        List<Fragment> fragmentList=new ArrayList<>();
        List<String> listTitle=new ArrayList<>();
        for(int i = 0; i<mRealmManager.size(Tinhot.class); i++){
            fragmentList.add(ListNewsFragment.newInstance(mRealmManager.findAll(Tinhot.class).get(i).getUrl()));
            listTitle.add(mRealmManager.findAll(Tinhot.class).get(i).getTitle());
        }
        TabPageAdapter tabPageAdapter=new TabPageAdapter(getFragmentManager(),fragmentList,listTitle);
        mViewPager.setAdapter(tabPageAdapter);
        mViewPager.setOffscreenPageLimit(0);
        mTabLayout.setupWithViewPager(mViewPager);
        PerformanceLog.stop("stop11:initViews");
    }

    @Override
    public HomeComponent getComponent() {
        if(mHomeComponent == null) {
            mHomeComponent = DaggerHomeComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .fragmentModule(getFragmentModule())
                    .dataFragmentModule(new DataFragmentModule(getContext()))
                    .build();
        }
        return mHomeComponent;
    }
}
