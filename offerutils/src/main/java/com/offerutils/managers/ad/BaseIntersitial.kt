package com.offerutils.managers.ad

import android.content.Context

interface BaseInterstitial {

    fun init(context: Context)

    fun setOnCloseListener(onAdCloseListener: (() -> Unit)? = null)

    fun isReady(): Boolean

    fun show(context: Context)

    fun makeRequest(context: Context)
}