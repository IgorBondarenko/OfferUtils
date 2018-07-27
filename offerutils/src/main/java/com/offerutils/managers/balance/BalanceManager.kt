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

    var currency = "Coins"

    var currentBalance: currencyType
        get() = dataManager.getData(DataManager.Type.COINS_BALANCE, 0 as currencyType)
        set(value) {
            dataManager.saveData(DataManager.Type.COINS_BALANCE, value)
        }

    constructor(activity: Activity, dataManager: DataManager, animationManager: AnimationManager, textView: TextView?, @RawRes coinsDropSound: Int = 0) : this(dataManager) {
        this.activity = activity
        this.animationManager = animationManager
        this.balanceView = textView
        this.soundRes = coinsDropSound
    }

    fun subtractFromBalance(amount: currencyType){
        subtractFromBalanceRealization(amount)
        activity?.runOnUiThread {
            animationManager?.changeText(balanceView, "$currentBalance", AnimationManager.Direction.BOTTOM_TO_TOP)
        }
    }

    protected abstract fun subtractFromBalanceRealization(amount: currencyType)

    fun addToBalance(value: currencyType, showToast: Boolean = true, customToast: String = "$value $currency added to your balance"){
        addToBalanceRealization(value)
        activity?.runOnUiThread {
            animationManager?.changeText(balanceView, currentBalance.toString(), AnimationManager.Direction.TOP_TO_BOTTOM)

            if (showToast) activity?.showLongToast(customToast)
            if (dataManager.getData(DataManager.Type.SETTINGS_SOUND, true)) {
                if (soundRes != 0) MediaPlayer.create(activity, soundRes)?.start()
            }
        }
    }

    protected abstract fun addToBalanceRealization(value: currencyType)

    /**
     * call in activity onResume()
     */
    fun setCoinsCountInToolbar(activity: Activity) = activity.runOnUiThread {
        balanceView?.text = currentBalance.toString()
    }

    /**
     * call in fragment onResume()
     */
    fun setBalanceCountInFragmentToolbar(fragment: Fragment) = fragment.activity?.runOnUiThread {
        balanceView?.text = currentBalance.toString()
    }

    //fun getBalance(): currencyType = currentBalance

}