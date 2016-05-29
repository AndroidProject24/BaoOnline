package com.toan_it.library.library.fragment.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;
import com.toan_it.library.library.BaseApplication;
import com.toan_it.library.library.utils.Logger;
import com.toan_it.library.skinloader.base.SkinBaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Toan.IT
 * Date:22/5/2016
 */
public abstract class BaseFragment extends SkinBaseFragment{
    private View mContentView;
    private Context mContext;
    private ProgressDialog mProgressDialog;
    private Subscription subscription;
    private CompositeSubscription mCompositeSubscription;
    private Unbinder unbinder;
    protected String TAG = getTAG();
    protected abstract String getTAG();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.d(TAG);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(TAG);
        if (null == mContentView) {
            mContentView = inflater.inflate(setLayoutResourceID(),container, false);
        }
        unbinder = ButterKnife.bind(this, mContentView);
        mContext = getContext();
        mProgressDialog = new ProgressDialog(getMContext());
        mProgressDialog.setCanceledOnTouchOutside(false);
        initBase();
        injectViews();
        injectData();
        return mContentView;
    }
    protected abstract int setLayoutResourceID();

    protected abstract void injectData();
    private void initBase() {
        Logger.initTag(TAG,true);
    }

    protected abstract void injectViews();

    protected View getContentView() {
        return mContentView;
    }

    private Context getMContext() {
        return mContext;
    }

    protected ProgressDialog getProgressDialog() {
        return mProgressDialog;
    }


    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }

    public void addSubscription(Subscription s) {
        if (s == null) {
            return;
        }
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.d(TAG);
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.d(TAG);
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d(TAG);
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.d(TAG);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.d(TAG);
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d(TAG);
        unsubscribe();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.d(TAG);
    }
    protected void checkException(Throwable e) {
        //NetUtils.checkHttpException(getContext(), e, mRootView);
    }

    private void unsubscribe() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
        RefWatcher refWatcher = BaseApplication.getRefWatcher();
        refWatcher.watch(this);
    }
    @Override
    public String toString() {
        return getClass().getSimpleName() + " @" + Integer.toHexString(hashCode());
    }
}
