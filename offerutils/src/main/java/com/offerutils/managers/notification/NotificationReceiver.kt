package com.offerutils.managers.notification

import android.app.Notification
import android.app.NotificationManager
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import com.offerutils.Utils
import com.offerutils.managers.notification.NotificationManager.Companion.EXTRA_PUSH_ACTION
import com.offerutils.managers.notification.NotificationManager.Companion.EXTRA_PUSH_ID
import com.offerutils.managers.notification.NotificationManager.Companion.EXTRA_PUSH_LARGE_ICON
import com.offerutils.managers.notification.NotificationManager.Companion.EXTRA_PUSH_MESSAGE
import com.offerutils.managers.notification.NotificationManager.Companion.EXTRA_PUSH_SMALL_ICON

class NotificationReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {

        val notificationBuilder = Notification.Builder(context)

        val defaultIcon = context.packageManager.getApplicationIcon(context.applicationContext?.packageName)

        val message = intent?.getStringExtra(EXTRA_PUSH_MESSAGE)
        val smallIcon = intent?.getIntExtra(EXTRA_PUSH_SMALL_ICON, 0) ?: 0
        val largeIcon = intent?.getIntExtra(EXTRA_PUSH_LARGE_ICON, 0) ?: 0

        val notificationId = intent?.getIntExtra(EXTRA_PUSH_ID, 0) ?: 0

        Log.d("CURRENT_PACKAGE_", "${context.applicationContext?.packageName}")

        val splashIntent = context.packageManager?.getLaunchIntentForPackage(context.applicationContext?.packageName)?.let {
            it.action = Intent.ACTION_MAIN
            it.addCategory(Intent.CATEGORY_LAUNCHER)
            it.putExtra(EXTRA_PUSH_ACTION, true)
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        notificationBuilder
            .setContentTitle(Utils.getApplicationName(context))
            .setAutoCancel(true)
            .setContentText(message)
            .setSmallIcon(smallIcon)
            .setLargeIcon(largeIcon ifZero getBitmap(defaultIcon))
            .setStyle(Notification.BigTextStyle().bigText(message))
            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentIntent(PendingIntent.getActivity(context, notificationId, splashIntent, 0))

        with(context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                with(NotificationChannel("1", "Main channel", NotificationManager.IMPORTANCE_DEFAULT)) {
                    enableVibration(true)
                    createNotificationChannel(this)
                    notificationBuilder.setChannelId("1")
                }
            }

            notify(notificationId, notificationBuilder.build())
        }

    }

    private fun getBitmap(drawable: Drawable) = (drawable as BitmapDrawable).bitmap

    private infix fun Int.ifZero(value: Bitmap) = if(this == 0) value else BitmapFactory.decodeResource(Resources.getSystem(), this)

}