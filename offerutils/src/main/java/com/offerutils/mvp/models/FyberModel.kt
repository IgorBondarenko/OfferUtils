package com.offerutils.mvp.models

import com.offerutils.managers.DataManager

interface FyberModel {

    val dataManager: DataManager

    fun isFyberVideo(): Boolean =
        dataManager.getData(DataManager.Type.IS_FYBER_VIDEO, false)
            .apply { dataManager.saveData(DataManager.Type.IS_FYBER_VIDEO, false) }

    fun resetFyberVideo() = dataManager.saveData(DataManager.Type.IS_FYBER_VIDEO, false)

    fun setFyberVideo() = dataManager.saveData(DataManager.Type.IS_FYBER_VIDEO, true)

    fun saveAgreement(consent: Boolean) {
        dataManager.saveData(DataManager.Type.FYBER_CONSENT, consent)
    }

    fun getUserAgreement(): Boolean = dataManager.getData(DataManager.Type.FYBER_CONSENT, false)

}