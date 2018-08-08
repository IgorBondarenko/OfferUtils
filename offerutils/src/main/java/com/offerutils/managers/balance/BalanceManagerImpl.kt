package com.offerutils.managers.balance

import com.offerutils.managers.DataManager

class BalanceManagerInt(dataManager: DataManager): BalanceManager<Int>(dataManager){

    override val checkValue: (value: Int, saveValue: () -> Unit) -> Unit  = { value, saveValue -> if(value >= 0) saveValue() }

}

class BalanceManagerFloat(dataManager: DataManager): BalanceManager<Float>(dataManager){

    override val checkValue: (value: Float, saveValue: () -> Unit) -> Unit = { value, saveValue -> if(value >= 0f) saveValue() }

}