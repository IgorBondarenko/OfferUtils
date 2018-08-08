package com.offerutils.mvp.models

import com.offerutils.managers.DataManager

interface RuModel {

    val dataManager: DataManager

    /*var isRuShown: Boolean
        get() = dataManager.getData(DataManager.Type.SHOW_RATE_US_DIALOG, false)
        set(value) {
            dataManager.saveData(DataManager.Type.SHOW_RATE_US_DIALOG, value)
        }

    fun setRuShown(){
        isRuShown = true
    }*/

    var isRated: Boolean
        get() = dataManager.getData(DataManager.Type.RATE_US, false)
        set(value) {
            dataManager.saveData(DataManager.Type.RATE_US, value)
        }


    fun rateUs() {
        isRated = true
    }

}