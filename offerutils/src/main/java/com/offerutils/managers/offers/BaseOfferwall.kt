package com.offerutils.managers.offers

import android.app.Activity
import android.util.Base64

interface BaseOfferwall<currencyType: Number> {

    var bonusReward: (() -> Unit)?
    var rewardListener: ((coinsAmount: currencyType, type: BaseOfferwall<currencyType>, bonusReward: (() -> Unit)?) -> Unit)?

    val name: String

    fun init(activity: Activity)
    fun onCreate(activity: Activity) {  }
    fun show(activity: Activity): Boolean
    fun onResume(activity: Activity) {  }
    fun onPause() {  }
    fun onPause(activity: Activity) {  }
    fun onStart(activity: Activity) { }
    fun onStop(activity: Activity) {  }
    fun onDestroy(activity: Activity) {  }

    companion object {
        fun getKey(key: String): String = Base64.decode((key).toByteArray(), Base64.URL_SAFE).toString(charset("UTF-8"))
    }

}