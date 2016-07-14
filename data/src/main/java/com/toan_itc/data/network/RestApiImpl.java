/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.toan_itc.data.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.toan_itc.data.local.DatabaseRealm;

import okhttp3.ResponseBody;
import retrofit2.http.Url;
import rx.Observable;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RestApiImpl implements RestApi {
  private final Context context;
  public RestApiImpl(Context context, DatabaseRealm userEntityJsonMapper) {
    if (context == null || userEntityJsonMapper == null) {
      throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
    }
    this.context = context.getApplicationContext();
   // this.userEntityJsonMapper = userEntityJsonMapper;
  }

  @RxLogObservable
  @Override
  public Observable<ResponseBody> GetRss(@Url String url) {
    return Observable.create(subscriber -> {
      /*if (isThereInternetConnection()) {
        try {
          String responseUserEntities = getUserEntitiesFromApi();
          if (responseUserEntities != null) {
            subscriber.onNext(userEntityJsonMapper.transformUserEntityCollection(
                    responseUserEntities));
            subscriber.onCompleted();
          } else {
            subscriber.onError(new NetworkConnectionException());
          }
        } catch (Exception e) {
          subscriber.onError(new NetworkConnectionException(e.getCause()));
        }
      } else {
        subscriber.onError(new NetworkConnectionException());
      }*/
    });
  }
  private boolean isThereInternetConnection() {
    boolean isConnected;
    ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

    return isConnected;
  }
}
