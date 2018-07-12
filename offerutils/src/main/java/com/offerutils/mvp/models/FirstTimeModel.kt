package com.offerutils.mvp.models

import com.offerutils.managers.DataManager

interface FirstTimeModel {

    val dataManager: DataManager

    var isFirstTime: Boolean
        get() = dataManager.getData(DataManager.Type.IS_FIRST_TIME, true)
        set(value) {
            dataManager.saveData(DataManager.Type.IS_FIRST_TIME, value)
        }

}