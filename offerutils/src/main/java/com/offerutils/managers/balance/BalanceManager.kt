package com.offerutils.managers.balance

import android.app.Activity
import android.media.MediaPlayer
import android.support.annotation.RawRes
import android.support.v4.app.Fragment
import android.widget.TextView
import com.offerutils.extensions.showLongToast
import com.offerutils.managers.AnimationManager
import com.offerutils.managers.DataManager

abstract class BalanceManager<currencyType : Number>(val dataManager: DataManager) {

    private var activity: Activity? = null
    private var animationManager: AnimationManager? = null
    private var balanceView: TextView? = null
    private var soundRes: Int = 0

    constructor(dataManager: DataManager, activity: Activity, animationManager: AnimationManager, textView: TextView, @RawRes coinsDropSound: Int = 0) : this(dataManager) {
        this.activity = activity
        this.animationManager = animationManager
        this.balanceView = textView
        this.soundRes = coinsDropSound
    }

    private val currency = "Coins"

    abstract fun subtractCoins(amount: currencyType)

    protected fun subtractCoinsInteraction() {
        activity?.runOnUiThread {
            animationManager?.changeText(balanceView, "${getBalance()}", AnimationManager.Direction.BOTTOM_TO_TOP)
        }
    }

    abstract fun addCoins(value: currencyType, showToast: Boolean = true, customToast: String = "$value $currency added to your balance")

    protected fun addCoinsInteraction(value: currencyType, showToast: Boolean = true, customToast: String = "$value $currency added to your balance") {
        activity?.runOnUiThread {
            animationManager?.changeText(balanceView, getBalance().toString(), AnimationManager.Direction.TOP_TO_BOTTOM)

            if (showToast) activity?.showLongToast(customToast)
            if (dataManager.getData(DataManager.Type.SETTINGS_SOUND, true)) {
                if (soundRes != 0) MediaPlayer.create(activity, soundRes)?.start()
            }
        }
    }

    fun setCoinsCountInToolbar(activity: Activity, textView: TextView) = activity.runOnUiThread {
        textView.text = getBalance().toString()
    }

    fun setCoinsCountInToolbar(fragment: Fragment, textView: TextView) = activity?.runOnUiThread {
        textView.text = getBalance().toString()
    }

    abstract fun getBalance(): currencyType

}