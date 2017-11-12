package com.nandawperdana.kotlinmvp.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v4.content.LocalBroadcastManager
import com.nandawperdana.kotlinmvp.AndroidApplication

/**
 * Created by nandawperdana.
 */
class ConnectivityReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, arg1: Intent) {
        //        int type = getConnectivityStatus(context);
        //        boolean isConnected = false;
        //        if (type != TYPE_NOT_CONNECTED)
        //            isConnected = true;

        val isConnected = isNetworkConnected(context)

        val networkStateIntent = Intent(NETWORK_AVAILABLE_ACTION)
        networkStateIntent.putExtra(IS_NETWORK_AVAILABLE, isConnected)
        LocalBroadcastManager.getInstance(context).sendBroadcast(networkStateIntent)
        //        if (connectivityReceiverListener != null) {
        //            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        //        }
    }

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    companion object {
        val NETWORK_AVAILABLE_ACTION = "Connectivity Receiver"
        val IS_NETWORK_AVAILABLE = "isNetworkAvailable"
        //    public static ConnectivityReceiverListener connectivityReceiverListener;

        var TYPE_WIFI = 1
        var TYPE_MOBILE = 2
        var TYPE_NOT_CONNECTED = 0

        fun getConnectivityStatus(context: Context): Int {
            val cm = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork = cm.activeNetworkInfo
            if (null != activeNetwork) {
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI)
                    return TYPE_WIFI

                if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
                    return TYPE_MOBILE
            }
            return TYPE_NOT_CONNECTED
        }

        fun getConnectivityStatusString(context: Context): String? {
            val conn = getConnectivityStatus(context)
            var status: String? = null
            if (conn == TYPE_WIFI) {
                status = "Wifi enabled"
            } else if (conn == TYPE_MOBILE) {
                status = "Mobile data enabled"
            } else if (conn == TYPE_NOT_CONNECTED) {
                status = "Not connected to Internet"
            }
            return status
        }

        val isConnected: Boolean
            get() {
                val cm = AndroidApplication.getInstance.applicationContext
                        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = cm.activeNetworkInfo
                return activeNetwork != null && activeNetwork.isConnectedOrConnecting
            }
    }
}
