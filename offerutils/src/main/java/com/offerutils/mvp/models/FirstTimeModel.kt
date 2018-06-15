package com.offerutils.mvp.models

import com.offerutils.managers.DataManager

interface FirstTimeModel {

    val dataManager: DataManager

    fun isFirstTime() : Boolean = dataManager.getData(DataManager.Type.IS_FIRST_TIME, true)

    fun setNotFirstTime() = dataManager.saveData(DataManager.Type.IS_FIRST_TIME, false)

}