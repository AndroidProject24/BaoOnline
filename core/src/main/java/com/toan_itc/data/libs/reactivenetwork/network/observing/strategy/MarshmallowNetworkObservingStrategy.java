/*
 * Copyright (C) 2016 Piotr Wittchen
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
package com.toan_itc.data.libs.reactivenetwork.network.observing.strategy;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.PowerManager;
import android.util.Log;

import com.toan_itc.data.libs.reactivenetwork.Connectivity;
import com.toan_itc.data.libs.reactivenetwork.network.observing.NetworkObservingStrategy;

import rx.Observable;
import rx.functions.Action0;
import rx.subjects.PublishSubject;

import static com.toan_itc.data.libs.reactivenetwork.ReactiveNetwork.LOG_TAG;


/**
 * Network observing strategy for devices with Android Marshmallow (API 23) or higher.
 * Uses Network Callback API and handles Doze mode.
 */
@TargetApi(23) public class MarshmallowNetworkObservingStrategy
    implements NetworkObservingStrategy {
  private ConnectivityManager.NetworkCallback networkCallback;
  private PublishSubject<Connectivity> connectivitySubject = PublishSubject.create();
  private BroadcastReceiver idleReceiver;

  @Override public Observable<Connectivity> observeNetworkConnectivity(final Context context) {
    final String service = Context.CONNECTIVITY_SERVICE;
    final ConnectivityManager manager = (ConnectivityManager) context.getSystemService(service);
    networkCallback = createNetworkCallback(context);

    registerIdleReceiver(context);

    final NetworkRequest request =
        new NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)
            .build();

    manager.registerNetworkCallback(request, networkCallback);

    return connectivitySubject.asObservable().onBackpressureLatest().doOnUnsubscribe(new Action0() {
      @Override public void call() {
        tryToUnregisterCallback(manager);
        tryToUnregisterReceiver(context);
      }
    }).startWith(Connectivity.create(context)).distinctUntilChanged();
  }

  private void registerIdleReceiver(final Context context) {
    final IntentFilter filter = new IntentFilter(PowerManager.ACTION_DEVICE_IDLE_MODE_CHANGED);
    idleReceiver = new BroadcastReceiver() {
      @Override public void onReceive(final Context context, final Intent intent) {
        if (isIdleMode(context)) {
          connectivitySubject.onNext(Connectivity.create());
        } else {
          connectivitySubject.onNext(Connectivity.create(context));
        }
      }
    };
    context.registerReceiver(idleReceiver, filter);
  }

  private boolean isIdleMode(final Context context) {
    final String packageName = context.getPackageName();
    final PowerManager manager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
    boolean isIgnoringOptimizations = manager.isIgnoringBatteryOptimizations(packageName);
    return manager.isDeviceIdleMode() && !isIgnoringOptimizations;
  }

  private void tryToUnregisterCallback(final ConnectivityManager manager) {
    try {
      manager.unregisterNetworkCallback(networkCallback);
    } catch (Exception exception) {
      onError("could not unregister network callback", exception);
    }
  }

  private void tryToUnregisterReceiver(Context context) {
    try {
      context.unregisterReceiver(idleReceiver);
    } catch (Exception exception) {
      onError("could not unregister receiver", exception);
    }
  }

  @Override public void onError(final String message, final Exception exception) {
    Log.e(LOG_TAG, message, exception);
  }

  private ConnectivityManager.NetworkCallback createNetworkCallback(final Context context) {
    return new ConnectivityManager.NetworkCallback() {
      @Override public void onAvailable(Network network) {
        connectivitySubject.onNext(Connectivity.create(context));
      }

      @Override public void onLost(Network network) {
        connectivitySubject.onNext(Connectivity.create(context));
      }
    };
  }
}
