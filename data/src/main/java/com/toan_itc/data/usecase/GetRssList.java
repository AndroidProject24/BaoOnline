package com.toan_itc.data.usecase;

import com.tickaroo.tikxml.TikXml;
import com.toan_itc.data.executor.PostExecutionThread;
import com.toan_itc.data.executor.ThreadExecutor;
import com.toan_itc.data.model.rss.RssChannel;
import com.toan_itc.data.model.rss.RssFeed;
import com.toan_itc.data.network.RestApi;
import com.toan_itc.data.utils.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import okio.Buffer;
import rx.Observable;

public class GetRssList extends UseCase {
    private final String linkRss;
    private final RestApi mRestApi;

    @Inject
    public GetRssList(String linkRss,RestApi restApi, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.linkRss=linkRss;
        this.mRestApi = restApi;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return this.mRestApi.GetRss(this.linkRss)
                .map(responseBody -> {
                    RssChannel rssChannel=null;
                    try {
                        TikXml parse = new TikXml.Builder().exceptionOnUnreadXml(false).build();
                        rssChannel = parse.read(new Buffer().writeUtf8(parse(responseBody)), RssFeed.class).getChannel();
                        Logger.e(rssChannel.getItem().toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return rssChannel;
                });
    }
    private String parse(ResponseBody responseBody){
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
        return sb.toString();
    }
}
