package com.offerutils.mvp.models

import com.offerutils.managers.DataManager

interface WalletModel{

    val dataManager: DataManager

    var wallet : String
        get() = dataManager.getData(DataManager.Type.USER_WALLET, "")
        set(value) {
            dataManager.saveData(DataManager.Type.USER_WALLET, value)
        }

}