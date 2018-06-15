package com.offerutils.mvp.models

import com.offerutils.managers.DataManager
import com.offerutils.managers.offers.OfferwallManager

interface OfferwallModel : RuModel, FyberModel {

    fun getOffers(requiredOffers: Array<OfferwallManager.Offer>): Array<OfferwallManager.Offer> {
        val offersMap = hashMapOf(Pair(OfferwallManager.Offer.FYBER_VIDEO, DataManager.Type.SETTINGS_FYBER), Pair(OfferwallManager.Offer.APPLOVIN, DataManager.Type.SETTINGS_APPLOVIN))
        return requiredOffers
            .filter { offersMap.containsKey(it) }
            .filter { dataManager.getData(it.name, true) }
            .toTypedArray()
    }

}
