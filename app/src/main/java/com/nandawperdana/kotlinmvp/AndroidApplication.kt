package com.nandawperdana.kotlinmvp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by nandawperdana.
 */
open class AndroidApplication : Application() {

    var connected: Boolean = false

    companion object {
        private var instance: AndroidApplication? = null

        /**
         * singleton class instance
         */
        val getInstance: AndroidApplication
            get() {
                if (instance == null) {
                    synchronized(AndroidApplication::class.java) {
                        if (instance == null) {
                            instance = AndroidApplication()
                        }
                    }
                }
                return instance!!
            }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    fun isConnected(): Boolean {
        val cm = applicationContext
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        connected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        return connected
    }

}