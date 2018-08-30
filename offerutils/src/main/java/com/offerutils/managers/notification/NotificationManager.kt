package com.offerutils.managers.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.DrawableRes

class NotificationManager {

    companion object {
        const val EXTRA_PUSH_ID = "extra_id"
        const val EXTRA_PUSH_ACTION = "extra_action"
        const val EXTRA_PUSH_MESSAGE = "extra_message"
        const val EXTRA_PUSH_SMALL_ICON = "extra_small_icon"
        const val EXTRA_PUSH_LARGE_ICON = "extra_large_icon"
    }

    fun start(context: Context, type: SingleNotification) =
        context.doWithAlarmManager(type) { alarmManager, pendingIntent ->
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + type.delay, pendingIntent)
        }

    fun startRepeating(context: Context, type: RepeatingNotification) =
        context.doWithAlarmManager(type) { alarmManager, pendingIntent ->
            alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis() + type.delay, type.delay, pendingIntent)
        }

    fun cancel(context: Context, type: SingleNotification) =
        context.doWithAlarmManager(type) { alarmManager, pendingIntent ->
            alarmManager.cancel(pendingIntent)
        }


    private fun Context.doWithAlarmManager(type: NotificationType, alarmManagerAction: (alarmManager: AlarmManager, pendingIntent: PendingIntent) -> Unit) {
        with(Intent()) {
            this.action = type.action
            putExtra(EXTRA_PUSH_ID, type.notificationId)
            putExtra(EXTRA_PUSH_ACTION, type.action)
            putExtra(EXTRA_PUSH_MESSAGE, type.message)
            putExtra(EXTRA_PUSH_SMALL_ICON, type.smallIcon)
            putExtra(EXTRA_PUSH_LARGE_ICON, type.largeIcon)

            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val pendingIntent = PendingIntent.getBroadcast(this@doWithAlarmManager, 0, this, PendingIntent.FLAG_UPDATE_CURRENT)
            alarmManagerAction(alarmManager, pendingIntent)
        }
    }
}

data class SingleNotification(override var notificationId: Int, override var action: String, override var delay: Long, override var message: String, @DrawableRes override var smallIcon: Int, @DrawableRes override var largeIcon: Int) :
    NotificationType(notificationId, action, delay, message, smallIcon, largeIcon)

data class RepeatingNotification(override var notificationId: Int, override var action: String, override var delay: Long, override var message: String, @DrawableRes override var smallIcon: Int, @DrawableRes override var largeIcon: Int) :

    NotificationType(notificationId, action, delay, message, smallIcon, largeIcon)

abstract class NotificationType(open val notificationId: Int, open val action: String, open val delay: Long, open var message: String, @DrawableRes open var smallIcon: Int, @DrawableRes open var largeIcon: Int)

