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

    override fun subtractFromBalanceRealization(amount: Int) {
        currentBalance -= amount
    }

    override fun addToBalanceRealization(value: Int) {
        currentBalance += value
    }

}

class BalanceManagerFloat : BalanceManager<Float> {

    constructor(dataManager: DataManager) : super(dataManager)

    constructor(activity: Activity, dataManager: DataManager, animationManager: AnimationManager, textView: TextView?, @RawRes coinsDropSound: Int) :
            super(activity, dataManager, animationManager, textView, coinsDropSound)

    override fun subtractFromBalanceRealization(amount: Float) {
        currentBalance -= amount
    }

    override fun addToBalanceRealization(value: Float) {
        currentBalance += value
    }

}