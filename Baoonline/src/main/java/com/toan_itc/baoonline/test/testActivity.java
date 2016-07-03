package com.toan_itc.baoonline.test;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.toan_it.library.library.BaseApplication;
import com.toan_it.library.library.activity.BaseActivity;
import com.toan_it.library.library.data.networking.RestData;
import com.toan_it.library.library.libs.image.ImageLoaderListener;
import com.toan_it.library.library.mvp.model.toan;
import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.injector.DaggerActivityComponent;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 02/07/2016
 */

public class testActivity extends BaseActivity {
    @Inject
    RestData mRestData;
    @Inject
    ImageLoaderListener mImageLoaderListener;
    RecyclerView recycler;

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void initViews() {
        recycler=(RecyclerView)findViewById(R.id.recycler);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.test;
    }

    @Override
    protected void initData() {
        mRestData.gettest()
                .subscribe(new Subscriber<List<toan>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<toan> toen) {
                        UsersAdapter usersAdapter=new UsersAdapter(testActivity.this,mImageLoaderListener);
                        usersAdapter.setUsersCollection(toen);
                        recycler.setLayoutManager(new LinearLayoutManager(testActivity.this,LinearLayoutManager.VERTICAL,false));
                        recycler.setAdapter(usersAdapter);
                    }
                });
    }

    @Override
    protected void injectDependencies() {
        DaggerActivityComponent.builder()
                .applicationComponent(BaseApplication.get(this).getApplicationComponent())
                .build().inject(this);
    }
}
