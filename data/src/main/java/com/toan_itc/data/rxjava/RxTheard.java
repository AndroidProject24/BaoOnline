package com.toan_itc.data.rxjava;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.toan_itc.baoonline.library.performance.BackgroundThread;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */

public class RxTheard {
    private static BackgroundThread backgroundthread;
    public static Observable<Void> RxmMainThread(){
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<Void> RxThread() {
        if(backgroundthread==null) {
            backgroundthread = new BackgroundThread();
            backgroundthread.start();
        }
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                subscriber.onCompleted();
            }
        })
                .observeOn(AndroidSchedulers.from(backgroundthread.getLooper()));
    }
    protected void test(){
        EditText editText=null;
        TextView textView=null;
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                assert editText != null;
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
                    }

                    @Override
                    public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                        subscriber.onNext(s.toString());
                    }

                    @Override
                    public void afterTextChanged(final Editable s) {
                    }
                });
            }
        })
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(final String s) {
                        textView.setText("Output : " + s);
                    }
                });
    }
    //RXbinding
  protected void test1(){
        EditText editText=null;
        TextView textView=null;
        RxTextView.afterTextChangeEvents(editText)
                .debounce(1000,TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvChangeEvent -> {
                    assert textView != null;
                    textView.setText("Output : " + tvChangeEvent.view()
                            .getText());
                });
    }

}
