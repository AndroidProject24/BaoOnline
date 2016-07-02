/*
package com.toan_itc.baoonline.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

*/
/**
 * Created by Toan.IT
 * Date: 06/06/2016
 *//*


public class RxJava {
    public static Observable<String> parse(){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
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
                subscriber.onNext();
                subscriber.onCompleted();
            }
        })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
*/
