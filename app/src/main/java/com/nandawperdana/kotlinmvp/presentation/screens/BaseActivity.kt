package com.nandawperdana.kotlinmvp.presentation.screens

import android.app.ProgressDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.nandawperdana.kotlinmvp.AndroidApplication
import com.nandawperdana.kotlinmvp.R
import com.nandawperdana.kotlinmvp.presentation.presenters.BaseView
import com.nandawperdana.kotlinmvp.util.ConnectivityReceiver

open class BaseActivity : AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intentFilter = IntentFilter(ConnectivityReceiver.NETWORK_AVAILABLE_ACTION)
        LocalBroadcastManager.getInstance(this).registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val isConnected = intent.getBooleanExtra(ConnectivityReceiver.IS_NETWORK_AVAILABLE, false)
                AndroidApplication.getInstance.connected = isConnected
                if (!isConnected)
                    showToast(getString(R.string.message_no_internet))
            }
        }, intentFilter)
    }

    override fun showProgress(flag: Boolean) {
    }

    override fun showToast(message: String?) {
        val snackbar = message?.let {
            Snackbar.make(findViewById(android.R.id.content),
                    it, Snackbar.LENGTH_SHORT)
        }
        val sbView = snackbar?.view
        val textView = sbView?.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        snackbar.show()
    }

    override fun showError(title: String?, message: String?) {
        if (message != null) {
            showToast(message)
        } else {
            showToast(getString(R.string.message_failed_request_general))
        }
    }
}
