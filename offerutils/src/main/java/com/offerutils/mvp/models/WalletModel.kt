package com.offerutils.mvp.models

import com.offerutils.managers.DataManager

interface WalletModel{

    val dataManager: DataManager

    val wallet : String
        get() = dataManager.getData(DataManager.Type.USER_WALLET, "")

    fun saveWallet(wallet: String) = dataManager.saveData(DataManager.Type.USER_WALLET, wallet)

}