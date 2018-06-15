package com.offerutils.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

fun Activity.getBooleanExtra(action: String) = intent.getBooleanExtra(action, false)

fun Activity.getIntentWithFlags(intentClass: Class<*>, flags: Int = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK) =
    Intent(this, intentClass).apply { setFlags(flags) }

fun Context.isOnline(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val nInfo = cm.activeNetworkInfo
    return nInfo != null && nInfo.isConnectedOrConnecting
}

fun Activity.showLongToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()