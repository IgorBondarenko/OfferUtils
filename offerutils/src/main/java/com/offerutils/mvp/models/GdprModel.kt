package com.offerutils.mvp.models

import com.offerutils.managers.DataManager
import com.offerutils.managers.offers.OfferwallManager

interface GdprModel{

    val dataManager: DataManager

    fun saveGdpr(offer: OfferwallManager.Offer, consent: Boolean) = dataManager.saveData(offer.name + "_pp", consent)

    fun getGdpr(offer: OfferwallManager.Offer) =  dataManager.getData(offer.name + "_pp", false)

}