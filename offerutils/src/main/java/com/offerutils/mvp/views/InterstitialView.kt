package com.offerutils.mvp.views

import android.app.Activity
import com.offerutils.managers.ad.AdManager

interface InterstitialView {

    val adManager: AdManager

    fun Activity.checkInterstitial(action: () -> Unit) {
        when {
            adManager.isReady -> adManager.showInterstitial(this) { action() }
            else -> action()
        }
    }

}