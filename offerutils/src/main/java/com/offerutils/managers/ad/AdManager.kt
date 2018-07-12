package com.offerutils.managers.ad

import android.content.Context

object AdManager {

    private val interstitialsHashMap = HashMap<AdManager.Interstitial, BaseInterstitial>()

    enum class Interstitial {
        APPLOVIN, AMAZON, ADMOB, STARTAPP, CUSTOM_AD1, CUSTOM_AD2, CUSTOM_AD3
    }

    fun addInterstitial(name: Interstitial, interstitial: BaseInterstitial) =
        interstitialsHashMap.put(name, interstitial).let { this }

    fun init(context: Context) {
        interstitialsHashMap.values.forEach { it.init(context) }
    }

    fun isReady(): Boolean = interstitialsHashMap.values.firstOrNull { it.isReady() } != null

    fun showInterstitial(context: Context, onAdCloseListener: (() -> Unit)? = null) {
        interstitialsHashMap.values.forEach { it.setOnCloseListener(onAdCloseListener) }
        interstitialsHashMap.values.firstOrNull {
            it.isReady().apply { if (!this) it.makeRequest(context) }
        }?.show(context)
    }

}