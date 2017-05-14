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
package com.toan_itc.data.libs.reactivenetwork;

import android.Manifest;
import android.content.Context;
import android.support.annotation.RequiresPermission;

import com.toan_itc.data.libs.reactivenetwork.internet.observing.InternetObservingStrategy;
import com.toan_itc.data.libs.reactivenetwork.internet.observing.error.DefaultErrorHandler;
import com.toan_itc.data.libs.reactivenetwork.internet.observing.error.ErrorHandler;
import com.toan_itc.data.libs.reactivenetwork.internet.observing.strategy.SocketInternetObservingStrategy;
import com.toan_itc.data.libs.reactivenetwork.network.observing.NetworkObservingStrategy;
import com.toan_itc.data.libs.reactivenetwork.network.observing.strategy.LollipopNetworkObservingStrategy;
import com.toan_itc.data.libs.reactivenetwork.network.observing.strategy.MarshmallowNetworkObservingStrategy;
import com.toan_itc.data.libs.reactivenetwork.network.observing.strategy.PreLollipopNetworkObservingStrategy;

import rx.Observable;
/**
 * ReactiveNetwork is an Android library
 * listening network connection state and change of the WiFi signal strength
 * with RxJava Observables. It can be easily used with RxAndroid.
 */
public class ReactiveNetwork {
  public final static String LOG_TAG = "ReactiveNetwork";
  private static final String DEFAULT_PING_HOST = "www.google.com";
  private static final int DEFAULT_PING_PORT = 80;
  private static final int DEFAULT_PING_INTERVAL_IN_MS = 2000;
  private static final int DEFAULT_INITIAL_PING_INTERVAL_IN_MS = 0;
  private static final int DEFAULT_PING_TIMEOUT_IN_MS = 2000;

  public ReactiveNetwork() {
  }

  /**
   * Creates a new instance of the ReactiveNetwork class
   *
   * @return ReactiveNetwork object
   */
  public static ReactiveNetwork create() {
    return new ReactiveNetwork();
  }

  /**
   * Observes network connectivity. Information about network state, type and typeName are contained in
   * observed Connectivity object.
   *
   * @param context Context of the activity or an application
   * @return RxJava Observable with Connectivity class containing information about network state,
   * type and typeName
   */
  @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
  public static Observable<Connectivity> observeNetworkConnectivity(final Context context) {
    final NetworkObservingStrategy strategy;

    if (Preconditions.isAtLeastAndroidMarshmallow()) {
      strategy = new MarshmallowNetworkObservingStrategy();
    } else if (Preconditions.isAtLeastAndroidLollipop()) {
      strategy = new LollipopNetworkObservingStrategy();
    } else {
      strategy = new PreLollipopNetworkObservingStrategy();
    }

    return observeNetworkConnectivity(context, strategy);
  }

  /**
   * Observes network connectivity. Information about network state, type and typeName are contained in
   * observed Connectivity object. Moreover, allows you to define NetworkObservingStrategy.
   *
   * @param context Context of the activity or an application
   * @param strategy NetworkObserving strategy to be applied - you can use one of the existing
   * strategies {@link PreLollipopNetworkObservingStrategy},
   * {@link LollipopNetworkObservingStrategy} or create your own custom strategy
   * @return RxJava Observable with Connectivity class containing information about network state,
   * type and typeName
   */
  @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
  public static Observable<Connectivity> observeNetworkConnectivity(final Context context,
                                                                                                                 final NetworkObservingStrategy strategy) {
    Preconditions.checkNotNull(context, "context == null");
    Preconditions.checkNotNull(strategy, "strategy == null");
    return strategy.observeNetworkConnectivity(context);
  }

  /**
   * Observes connectivity with the Internet with default settings. It pings remote host
   * (www.google.com) at port 80 every 2 seconds with 2 seconds of timeout. This operation is used
   * for determining if device is connected to the Internet or not. Please note that this method is
   * less efficient than {@link #observeNetworkConnectivity(Context)} method and consumes data
   * transfer, but it gives you actual information if device is connected to the Internet or not.
   *
   * @return RxJava Observable with Boolean - true, when we have an access to the Internet
   * and false if not
   */
  @RequiresPermission(Manifest.permission.INTERNET)
  public static Observable<Boolean> observeInternetConnectivity() {
    return observeInternetConnectivity(DEFAULT_INITIAL_PING_INTERVAL_IN_MS,
        DEFAULT_PING_INTERVAL_IN_MS, DEFAULT_PING_HOST, DEFAULT_PING_PORT,
        DEFAULT_PING_TIMEOUT_IN_MS, new DefaultErrorHandler());
  }

  /**
   * Observes connectivity with the Internet with default settings,
   * but with custom InternetObservingStrategy. It pings remote host
   * (www.google.com) at port 80 every 2 seconds with 2 seconds of timeout. This operation is used
   * for determining if device is connected to the Internet or not. Please note that this method is
   * less efficient than {@link #observeNetworkConnectivity(Context)} method and consumes data
   * transfer, but it gives you actual information if device is connected to the Internet or not.
   *
   * @param strategy which implements InternetObservingStrategy
   * @return RxJava Observable with Boolean - true, when we have an access to the Internet
   * and false if not
   */
  @RequiresPermission(Manifest.permission.INTERNET)
  public static Observable<Boolean> observeInternetConnectivity(
      final InternetObservingStrategy strategy) {
    Preconditions.checkNotNull(strategy, "strategy == null");
    return strategy.observeInternetConnectivity(DEFAULT_INITIAL_PING_INTERVAL_IN_MS,
        DEFAULT_PING_INTERVAL_IN_MS, DEFAULT_PING_HOST, DEFAULT_PING_PORT,
        DEFAULT_PING_TIMEOUT_IN_MS, new DefaultErrorHandler());
  }

  /**
   * Observes connectivity with the Internet by opening socket connection with remote host
   *
   * @param intervalInMs in milliseconds determining how often we want to check connectivity
   * @param host for checking Internet connectivity
   * @param port for checking Internet connectivity
   * @param timeoutInMs for pinging remote host in milliseconds
   * @return RxJava Observable with Boolean - true, when we have connection with host and false if
   * not
   */
  @RequiresPermission(Manifest.permission.INTERNET)
  public static Observable<Boolean> observeInternetConnectivity(final int intervalInMs,
      final String host, final int port, final int timeoutInMs) {
    return observeInternetConnectivity(DEFAULT_INITIAL_PING_INTERVAL_IN_MS, intervalInMs, host,
        port, timeoutInMs, new DefaultErrorHandler());
  }

  /**
   * Observes connectivity with the Internet by opening socket connection with remote host
   *
   * @param initialIntervalInMs in milliseconds determining the delay of the first connectivity
   * check
   * @param intervalInMs in milliseconds determining how often we want to check connectivity
   * @param host for checking Internet connectivity
   * @param port for checking Internet connectivity
   * @param timeoutInMs for pinging remote host in milliseconds
   * @return RxJava Observable with Boolean - true, when we have connection with host and false if
   * not
   */
  @RequiresPermission(Manifest.permission.INTERNET)
  public static Observable<Boolean> observeInternetConnectivity(final int initialIntervalInMs,
      final int intervalInMs, final String host, final int port, final int timeoutInMs) {
    return observeInternetConnectivity(initialIntervalInMs, intervalInMs, host, port, timeoutInMs,
        new DefaultErrorHandler());
  }

  /**
   * Observes connectivity with the Internet by opening socket connection with remote host
   *
   * @param initialIntervalInMs in milliseconds determining the delay of the first connectivity
   * check
   * @param intervalInMs in milliseconds determining how often we want to check connectivity
   * @param host for checking Internet connectivity
   * @param port for checking Internet connectivity
   * @param timeoutInMs for pinging remote host in milliseconds
   * @param errorHandler for handling errors during connectivity check
   * @return RxJava Observable with Boolean - true, when we have connection with host and false if
   * not
   */
  @RequiresPermission(Manifest.permission.INTERNET)
  public static Observable<Boolean> observeInternetConnectivity(final int initialIntervalInMs,
      final int intervalInMs, final String host, final int port, final int timeoutInMs,
      final ErrorHandler errorHandler) {
    return observeInternetConnectivity(new SocketInternetObservingStrategy(), initialIntervalInMs,
        intervalInMs, host, port, timeoutInMs, errorHandler);
  }

  /**
   * Observes connectivity with the Internet by opening socket connection with remote host with
   * custom strategy implementation
   *
   * @param strategy for observing Internet connectivity
   * @param initialIntervalInMs in milliseconds determining the delay of the first connectivity
   * check
   * @param intervalInMs in milliseconds determining how often we want to check connectivity
   * @param host for checking Internet connectivity
   * @param port for checking Internet connectivity
   * @param timeoutInMs for pinging remote host in milliseconds
   * @param errorHandler for handling errors during connectivity check
   * @return RxJava Observable with Boolean - true, when we have connection with host and false if
   * not
   */
  @RequiresPermission(Manifest.permission.INTERNET)
  public static Observable<Boolean> observeInternetConnectivity(
      final InternetObservingStrategy strategy, final int initialIntervalInMs,
      final int intervalInMs, final String host, final int port, final int timeoutInMs,
      final ErrorHandler errorHandler) {
    Preconditions.checkNotNull(strategy, "strategy == null");
    return strategy.observeInternetConnectivity(initialIntervalInMs, intervalInMs, host, port,
        timeoutInMs, errorHandler);
  }
}
