package com.toan_itc.baoonline.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.tickaroo.tikxml.TikXml;
import com.toan_it.library.library.activity.base.BaseActivity;
import com.toan_itc.baoonline.BaoOnlineApplication;
import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.data.local.DatabaseRealm;
import com.toan_itc.baoonline.data.local.PreferencesHelper;
import com.toan_itc.baoonline.data.rxjava.RxBus;
import com.toan_itc.baoonline.data.service.RestClient;
import com.toan_itc.baoonline.data.service.Service;
import com.toan_itc.baoonline.injector.component.ActivityComponent;
import com.toan_itc.baoonline.injector.component.DaggerActivityComponent;
import com.toan_itc.baoonline.mvp.model.rss.RssFeed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import okio.Buffer;
import rx.Subscriber;

public class MainActivity extends BaseActivity {
    private BaseActivity mBaseActivity=this;
    private DatabaseRealm mDatabaseRealm;
    private PreferencesHelper mPreferencesHelper;
    private RxBus rxBus;
    private ActivityComponent mActivityComponent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RestClient restClient=RestClient.Creator.sRestClient(this);
        Service service=new Service(restClient);
        service.GetRss("http://vietnamnet.vn/rss/tin-noi-bat.rss")
                .map(responseBody -> {
                    BufferedReader reader = null;
                    StringBuilder sb = new StringBuilder();
                    try {
                        reader = new BufferedReader(new InputStreamReader(responseBody.byteStream()));
                        String line;
                        try {
                            while ((line = reader.readLine()) != null) {
                                sb.append(line);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    ;
                    return sb.toString();
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        try {
                            TikXml parse= new TikXml.Builder().exceptionOnUnreadXml(false).build();
                            RssFeed rssFeed=parse.read(new Buffer().writeUtf8(s), RssFeed.class);
                            Log.wtf("employee=", rssFeed.toString());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected void setUpView() {
        //ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), HomeFragment.newInstance(), R.id.contentFrame);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpData() {
        mDatabaseRealm=mActivityComponent.mDatabaseRealm();
        mDatabaseRealm.setup();
        mPreferencesHelper = mActivityComponent.mPreferencesHelper();
        rxBus=mActivityComponent.mRxBus();
        mDatabaseRealm.Get_News(this);
    }

    @Override
    protected void injector() {
        mActivityComponent= DaggerActivityComponent.builder()
                .applicationComponent(BaoOnlineApplication.get(this).getApplicationComponent())
                .build();
    }
}