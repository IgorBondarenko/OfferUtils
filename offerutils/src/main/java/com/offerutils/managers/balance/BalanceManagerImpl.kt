package com.offerutils.managers.balance

import android.app.Activity
import android.support.annotation.RawRes
import android.widget.TextView
import com.offerutils.managers.AnimationManager
import com.offerutils.managers.DataManager

class BalanceManagerInt : BalanceManager<Int> {

    constructor(dataManager: DataManager) : super(dataManager)

    constructor(activity: Activity, dataManager: DataManager, animationManager: AnimationManager, textView: TextView?, @RawRes coinsDropSound: Int) :
            super(activity, dataManager, animationManager, textView, coinsDropSound)


    override fun subtractCoins(amount: Int) {
        dataManager.saveData(DataManager.Type.COINS_BALANCE, getBalance() - amount)
        subtractCoinsInteraction()
    }

    override fun addCoins(value: Int, showToast: Boolean, currency: String, customToast: String) {
        dataManager.saveData(DataManager.Type.COINS_BALANCE, getBalance() + value)
        addCoinsInteraction(value, showToast, currency, customToast)
    }

    override fun getBalance(): Int = dataManager.getData(DataManager.Type.COINS_BALANCE, 0)

}

class BalanceManagerFloat : BalanceManager<Float> {

    constructor(dataManager: DataManager) : super(dataManager)

    constructor(activity: Activity, dataManager: DataManager, animationManager: AnimationManager, textView: TextView?, @RawRes coinsDropSound: Int) :
            super(activity, dataManager, animationManager, textView, coinsDropSound)

    override fun subtractCoins(amount: Float) {
        dataManager.saveData(DataManager.Type.COINS_BALANCE, getBalance() - amount)
        subtractCoinsInteraction()
    }

    override fun addCoins(value: Float, showToast: Boolean, currency: String, customToast: String) {
        dataManager.saveData(DataManager.Type.COINS_BALANCE, getBalance() + value)
        addCoinsInteraction(value, showToast, currency, customToast)
    }

    override fun getBalance(): Float = dataManager.getData(DataManager.Type.COINS_BALANCE, 0f)

}