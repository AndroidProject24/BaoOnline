/*
package com.toan_it.library.library.fragment;

import android.support.v4.widget.SwipeRefreshLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.toan_it.library.R;
import com.toan_it.library.library.utils.LogUtils;
import com.toan_it.library.library.utils.StringUtils;

import java.util.List;

*/
/**
 * Created by _SOLID
 * Date:2016/5/12
 * Time:15:50
 *//*

public abstract class SwipeRefreshViewFragment<T> extends BaseListFragment {

    XRecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_swiprefresh_recyclerview;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        mRecyclerView = $(R.id.recyclerview);
        mSwipeRefreshLayout = $(R.id.swipe_refresh_layout);

        mRecyclerView.setLayoutManager(setLayoutManager());
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                switchActionAndLoadData(ACTION_REFRESH);
            }
        });
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });

    }

    @Override
    protected void setUpData() {
        super.setUpData();
        switchActionAndLoadData(ACTION_PRE_LOAD);
    }

    @Override
    protected void onDataErrorReceived() {
        loadComplete();
    }

    @Override
    protected void onDataSuccessReceived(String result) {
        LogUtils.i(getMContext(), "onDataSuccessReceived:" + result);
        if (!StringUtils.isNullOrEmpty(result)) {
            List<T> list = parseData(result);

            mAdapter.addAll(list, mCurrentAction == ACTION_REFRESH);
            if (mCurrentAction != ACTION_PRE_LOAD) loadComplete();
            //mLLReloadWarp.setVisibility(View.GONE);
        } else {
            onDataErrorReceived();
        }
    }

    @Override
    protected void loadComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
*/
