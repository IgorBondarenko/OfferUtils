package com.offerutils.mvp.models

import com.offerutils.managers.DataManager

interface SoundSettingModel{

    val dataManager: DataManager

    val isSoundEnabled
            get() = dataManager.getData(DataManager.Type.SETTINGS_SOUND, true)

    fun setSoundSetting(isEnabled: Boolean) = dataManager.saveData(DataManager.Type.SETTINGS_SOUND, isEnabled)

}