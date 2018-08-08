package com.offerutils.extensions

import android.app.Activity
import android.app.NotificationManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.support.annotation.RawRes
import android.support.annotation.StringRes
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun Activity.getBooleanExtra(action: String) = intent.getBooleanExtra(action, false)

fun Activity.getBooleanExtra(@StringRes action: Int) = getBooleanExtra(getString(action))

fun Activity.getIntentWithFlags(intentClass: Class<*>, flags: Int = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK) =
    Intent(this, intentClass).apply { setFlags(flags) }

val Context.isOnline: Boolean
    get() = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo?.isConnectedOrConnecting
            ?: false

fun Context.showLongToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.showLongToast(@StringRes message: Int) = showLongToast(getString(message))

fun Activity.shareText(message: String, title: String = "Share") {
    with(Intent(Intent.ACTION_SEND)) {
        putExtra(Intent.EXTRA_TEXT, message)
        type = "text/plain"
        startActivity(Intent.createChooser(this, title))
    }
}

fun Activity.hideSoftKeyboard() {
    currentFocus?.let {
        with(getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager){
            hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }
}

fun Context.copyTextToBuffer(label: String, text: String) {
    with(getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager) {
        primaryClip = ClipData.newPlainText(label, text)
    }
}

fun Context.playSound(@RawRes soundRes: Int) {
    MediaPlayer.create(this, soundRes)?.start()
}

fun Activity.shareText(@StringRes message: Int, @StringRes title: Int) =
    shareText(getString(message), getString(title))

fun Activity.removeNotifications(vararg notificationsId: Int) {
    notificationsId.forEach {
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).cancel(it)
    }
}

inline fun <reified A : Activity> Context.startActivity(finishCurrent: Boolean = false) {
    startActivity(Intent(this, A::class.java))
    if (finishCurrent) (this as? Activity)?.finish()
}

inline fun <reified A : Activity> Activity.startActivityForResult(requestCode: Int) {
    startActivityForResult(Intent(this, A::class.java), requestCode)
}
