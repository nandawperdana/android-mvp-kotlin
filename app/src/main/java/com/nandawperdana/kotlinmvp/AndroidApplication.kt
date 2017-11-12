package com.nandawperdana.kotlinmvp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import io.realm.Realm

/**
 * Created by nandawperdana.
 */
open class AndroidApplication : Application() {

    var connected: Boolean = false

    override fun onCreate() {
        super.onCreate()

        instance = this
        Realm.init(this)
    }

    fun isConnected(): Boolean {
        val cm = applicationContext
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        connected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        return connected
    }

    companion object {
        var instance: AndroidApplication? = null

        /**
         * singleton class instance

         * @return APICallManager
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
}