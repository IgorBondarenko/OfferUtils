package com.offerutils.mvp.models

import com.offerutils.managers.DataManager

interface RuModel {

    val dataManager: DataManager

    fun isRuShown() = dataManager.getData(DataManager.Type.SHOW_RATE_US_DIALOG, false)

    fun setRuShown(){
        dataManager.saveData(DataManager.Type.SHOW_RATE_US_DIALOG, true)
    }

    fun isRated(): Boolean = dataManager.getData(DataManager.Type.RATE_US, false)

    fun rateUs() = dataManager.saveData(DataManager.Type.RATE_US, true)

}