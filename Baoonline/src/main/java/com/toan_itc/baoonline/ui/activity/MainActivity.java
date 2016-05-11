package com.toan_itc.baoonline.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.tickaroo.tikxml.TikXml;
import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.data.service.RestClient;
import com.toan_itc.baoonline.data.service.Service;
import com.toan_itc.baoonline.mvp.model.rss.RssFeed;
import com.toan_itc.baoonline.ui.fragment.HomeFragment;
import com.toan_itc.baoonline.utils.ActivityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import okio.Buffer;
import rx.Subscriber;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getComponent().inject(this);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), HomeFragment.newInstance(), R.id.contentFrame);
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
}