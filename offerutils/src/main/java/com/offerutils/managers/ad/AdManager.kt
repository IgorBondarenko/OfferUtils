package com.offerutils.managers.ad

import android.app.Activity

object AdManager {

    private val interstitialsHashMap = HashMap<AdManager.Interstitial, BaseInterstitial>()

    enum class Interstitial {
        APPLOVIN, AMAZON, ADMOB, STARTAPP, CUSTOM_AD1, CUSTOM_AD2, CUSTOM_AD3
    }

    fun addInterstitial(name: Interstitial, interstitial: BaseInterstitial) =
        interstitialsHashMap.put(name, interstitial).let { this }

    fun init(activity: Activity) {
        interstitialsHashMap.values.forEach { it.init(activity) }
    }

    val isReady: Boolean
        get() = interstitialsHashMap.values.firstOrNull()?.isReady ?: false

    fun showInterstitial(activity: Activity, onAdCloseListener: (() -> Unit)? = null, doAfter: (() -> Unit)? = null) {
        interstitialsHashMap.values.forEach { it.setOnCloseListener(onAdCloseListener ?: doAfter) }
        interstitialsHashMap.values.firstOrNull()?.let {
            when {
                it.isReady -> it.show(activity)
                else -> {
                    doAfter?.invoke()
                    it.makeRequest(activity)
                }
            }
        }
    }

}