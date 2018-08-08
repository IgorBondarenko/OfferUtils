package com.offerutils.mvp.models

import com.offerutils.managers.DataManager

interface FyberModel {

    val dataManager: DataManager

    var isFyberVideo: Boolean
        get() = dataManager.getData(DataManager.Type.IS_FYBER_VIDEO, false)
            .apply { dataManager.saveData(DataManager.Type.IS_FYBER_VIDEO, false) }
        set(value){
            dataManager.saveData(DataManager.Type.IS_FYBER_VIDEO, true)
        }

    fun resetFyberVideo() {
        isFyberVideo = false
    }

    /*var fyberAgreement: Boolean
        get() = dataManager.getData(DataManager.Type.FYBER_CONSENT, false)
        set(value) {
            dataManager.saveData(DataManager.Type.FYBER_CONSENT, value)
        }*/

}