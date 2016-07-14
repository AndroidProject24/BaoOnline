package com.toan_itc.baoonline.library.base;

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
import com.toan_it.library.skinloader.base.SkinBaseFragment;
import com.toan_itc.baoonline.library.BaseApplication;
import com.toan_itc.baoonline.library.injector.scope.HasComponent;
import com.toan_itc.baoonline.library.libs.loading.AVLoadingIndicatorView;
import com.toan_itc.data.utils.logger.Logger;

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
   /* @Inject
    Navigator navigator;*/
    private View mContentView;
    private Context mContext;
    private AVLoadingIndicatorView mAVLoadingIndicatorView;
    private Subscription subscription;
    private CompositeSubscription mCompositeSubscription;
    private Unbinder unbinder;
    protected String TAG = getTAG();
    protected abstract String getTAG();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null == mContentView) {
            mContentView = inflater.inflate(setLayoutResourceID(),container, false);
        }
        unbinder = ButterKnife.bind(this, mContentView);
        mContext = getContext();
        mAVLoadingIndicatorView=new AVLoadingIndicatorView(getContext());
        mAVLoadingIndicatorView.setIndicatorColor(R.color.colorPrimary);
        initBase();
        initViews();
        initData();
        return mContentView;
    }
    protected abstract int setLayoutResourceID();
    private void initBase() {

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

    protected void ShowLoading(boolean loading) {
        mAVLoadingIndicatorView.setVisibility(loading ? View.GONE : View.VISIBLE);;
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
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity())
                .getComponent());
    }
   /* @Override
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
*/
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
        try {
            if (this.subscription != null && !this.subscription.isUnsubscribed()) {
                this.subscription.unsubscribe();
            }
            if (this.mCompositeSubscription != null && !this.mCompositeSubscription.isUnsubscribed()) {
                this.mCompositeSubscription.unsubscribe();
            }
            RefWatcher refWatcher = BaseApplication.getRefWatcher();
            refWatcher.watch(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
