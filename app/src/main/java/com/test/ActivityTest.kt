package com.test

import android.app.Activity
import android.app.AlarmManager
import android.os.Bundle
import com.offerutils.extensions.launchInUIWithDelay
import com.offerutils.managers.DataManager
import com.offerutils.managers.notification.NotificationManager
import com.offerutils.managers.balance.BalanceManager
import com.offerutils.managers.offers.OfferwallManager
import com.offerutils.managers.offers.OfferwallsManagerFloat
import com.offerutils.mvp.views.OfferwallView
import kotlinx.android.synthetic.main.start.*

class ActivityTest : Activity(), OfferwallView<Float> {

    override val offerwallManager: OfferwallManager<Float> = OfferwallsManagerFloat

    lateinit var balanceManager: BalanceManager<Float>

    val notificationManager = NotificationManager()

    val dataManager = DataManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.start)

        image.setOnClickListener {  }

        launchInUIWithDelay(3000){
            image.setImageDrawable(resources.getDrawable(R.color.colorAccent))
            image.statePressed = resources.getDrawable(R.color.colorPrimary)
        }

        button.setOnClickListener {

            dataManager.saveData("test", "test")

        }

        notificationManager.start(this, NotificationManager.SingleNotification(
            1, "test.test.test", AlarmManager.INTERVAL_HOUR / 360, "test", R.drawable.abc_ic_star_half_black_48dp, R.drawable.ic_launcher_background
        ))
    }

}