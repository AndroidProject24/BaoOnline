/*
package com.toan_it.library.library.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.toan_it.library.R;
import com.toan_it.library.library.utils.StringUtils;

import java.util.List;

*/
/**
 * Created by _SOLID
 * Date:2016/4/18
 * Time:17:36
 * <p/>
 * common fragment for list data display ,and you can extends this fragment for everywhere you want to display list data
 *//*

public abstract class XRecyclerViewFragment<T> extends BaseListFragment {

    private static String TAG = "XRecyclerViewFragment";

    private XRecyclerView mRecyclerView;
    private LinearLayout mLLReloadWarp;
    private Button mBtnReload;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_xrecyclerview;
    }

    @Override
    protected void setUpView() {

        mLLReloadWarp = $(R.id.ll_reload_wrap);
        mBtnReload = $(R.id.btn_reload);
        mRecyclerView = $(R.id.recyclerview);

        mRecyclerView.setLayoutManager(setLayoutManager());
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotatePulse);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                switchActionAndLoadData(ACTION_REFRESH);
            }

            @Override
            public void onLoadMore() {
                switchActionAndLoadData(ACTION_LOAD_MORE);
            }
        });

        mBtnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLLReloadWarp.setVisibility(View.GONE);
                switchActionAndLoadData(ACTION_REFRESH);
            }
        });
    }


    @Override
    protected void setUpData() {
        //pre_loading data(if have cache)
        switchActionAndLoadData(ACTION_PRE_LOAD);
        mRecyclerView.setRefreshing(true);
    }

    @Override
    protected void onDataErrorReceived() {
        Log.i(TAG, "onDataErrorReceived");
        mLLReloadWarp.setVisibility(View.VISIBLE);
        loadComplete();
    }

    @Override
    protected void onDataSuccessReceived(String result) {
        Log.i(TAG, "onDataSuccessReceived");
        if (!StringUtils.isNullOrEmpty(result)) {
            List<T> list = parseData(result);
            mAdapter.addAll(list, mCurrentAction == ACTION_REFRESH);
            if (mCurrentAction != ACTION_PRE_LOAD) loadComplete();
            mLLReloadWarp.setVisibility(View.GONE);
        } else {
            onDataErrorReceived();
        }
    }

    @Override
    protected void loadComplete() {
        if (mCurrentAction == ACTION_REFRESH)
            mRecyclerView.refreshComplete();
        if (mCurrentAction == ACTION_LOAD_MORE)
            mRecyclerView.loadMoreComplete();
    }


}
*/
