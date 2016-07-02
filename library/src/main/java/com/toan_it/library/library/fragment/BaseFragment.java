package com.toan_it.library.library.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;
import com.toan_it.library.R;
import com.toan_it.library.library.BaseApplication;
import com.toan_it.library.library.libs.loading.AVLoadingIndicatorView;
import com.toan_it.library.library.utils.Logger;
import com.toan_it.library.skinloader.base.SkinBaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by Toan.IT
 * Date:22/5/2016
 */
public abstract class BaseFragment extends SkinBaseFragment{
    private View mContentView;
    private Context mContext;
    private AVLoadingIndicatorView mAVLoadingIndicatorView;
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
        mAVLoadingIndicatorView=new AVLoadingIndicatorView(getContext());
        mAVLoadingIndicatorView.setIndicatorColor(R.color.colorPrimary);
        initBase();
        initViews();
        injectDependencies();
        initData();
        return mContentView;
    }
    protected abstract int setLayoutResourceID();
    private void initBase() {
        Logger.initTag(TAG);
    }
    protected abstract void injectDependencies();
    protected abstract void initViews();
    protected abstract void initData();
    protected View getContentView() {
        return mContentView;
    }

    private Context getmContext() {
        return mContext;
    }

    protected AVLoadingIndicatorView getAVLoadingIndicatorView() {
        return mAVLoadingIndicatorView;
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
    protected void addFagment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId){
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment,fragment.getClass().getName());
        transaction.commit();
    }
    protected void replaceFagment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId){
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment,fragment.getClass().getName());
        transaction.commit();
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
        return getClass().getSimpleName();
    }
}
