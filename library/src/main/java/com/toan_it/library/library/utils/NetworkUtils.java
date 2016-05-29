package com.toan_it.library.library.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.toan_it.library.R;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by _SOLID
 * Date:2016/4/20
 * Time:16:23
 */
public class NetworkUtils {

    /**
     * 判断网络是否正常连接
     *
     * @param context
     * @return boolean true：网络连接正常 false：网络连接不正常
     */
    public static boolean isNetworkConnected(Context context) {
        if (null == context) {
            return false;
        }
        // 获取手机所有连接管理对象（包括wifi，net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            // 获取网络连接管理的对象
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 判断当前网络是否已经连接
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取当前网络连接的类型信息 1 wifi 2 移动网络 -1 无网络
     *
     * @return
     */
    public int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI ? 1
                        : 2;
            }
        }
        return -1;
    }

    /**
     * 判断当前wifi是否可用
     *
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @return
     */
    public boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    public static void checkHttpException(Context mContext, Throwable mThrowable, View mRootView) {
        String snack_action_to_setting = mContext.getString(R.string.snack_action_to_setting);
        if ((mThrowable instanceof UnknownHostException)) {
            String snack_message_net_error = mContext.getString(R.string.snack_message_net_error);
            showNetworkErrorSnackBar(mContext, mRootView, snack_message_net_error, snack_action_to_setting);
        } else if (mThrowable instanceof JSONException) {
            String snack_message_data_error = mContext.getString(R.string.snack_message_data_error);
            showNetworkErrorSnackBar(mContext, mRootView, snack_message_data_error, snack_action_to_setting);
        } else if (mThrowable instanceof SocketTimeoutException) {
            String snack_message_time_out = mContext.getString(R.string.snack_message_timeout_error);
            showNetworkErrorSnackBar(mContext, mRootView, snack_message_time_out, snack_action_to_setting);
        } else if (mThrowable instanceof ConnectException) {
            String snack_message_net_error = mContext.getString(R.string.snack_message_net_error);
            showNetworkErrorSnackBar(mContext, mRootView, snack_message_net_error, snack_action_to_setting);
        } else {
            String snack_message_unknown_error = mContext.getString(R.string.snack_message_unknown_error);
            //showSnackBar(mRootView,snack_message_unknown_error);
        }
    }

   /* private static Snackbar showSnackBar(View view, String message) {
        Snackbar snackbar=Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
        return snackbar;
    }*/
    private static void showNetworkErrorSnackBar(final Context context, View view, String message, String action) {
       /* Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction(action, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        context.startActivity(intent);
                    }
                })
                .show();*/
    }

}
