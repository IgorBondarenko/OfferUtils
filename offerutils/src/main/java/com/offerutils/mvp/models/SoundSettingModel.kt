package com.offerutils.mvp.models

import com.offerutils.managers.DataManager

interface SoundSettingModel {

    val dataManager: DataManager

    var isSoundEnabled
        get() = dataManager.getData(DataManager.Type.SETTINGS_SOUND, true)
        set(value) {
            dataManager.saveData(DataManager.Type.SETTINGS_SOUND, value)
        }

}