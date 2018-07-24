package com.offerutils.managers.ad

import android.app.Activity

interface BaseInterstitial {

    val isReady: Boolean

    fun init(activity: Activity)

    fun setOnCloseListener(onAdCloseListener: (() -> Unit)? = null)

    fun show(activity: Activity)

    fun makeRequest(activity: Activity)
}