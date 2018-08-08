package com.offerutils.managers.offers

import android.app.Activity
import kotlin.concurrent.thread

abstract class OfferwallManager<currencyType : Number> {

    private val offerwallOfferHashMap = HashMap<Offer, BaseOfferwall<currencyType>>()

    enum class Offer {
        ADCOLONY, VUNGLE, NATIVEX, OFFERTORO, MYOFFERS, ADXMI, SUPER_REWARDS, ADSCEND_MEDIA, FYBER_OFFERWALL, APPLOVIN, FYBER_VIDEO, UNITY, CHARBOOST, START_APP, ADGATE_VIDEO, ADGATE_OOFERWALL,
        CUSTOM_OFFER1, CUSTOM_OFFER2, CUSTOM_OFFER3, CUSTOM_VIDEO1, CUSTOM_VIDEO2, CUSTOM_VIDEO3,
    }

    fun addOfferwall(name: Offer, offerwall: BaseOfferwall<currencyType>) =
        offerwallOfferHashMap.put(name, offerwall).let { this }

    fun getOfferwall(offer: Offer, activity: Activity) = offerwallOfferHashMap[offer].let {
        it ?: run {
            offerwallOfferHashMap[offer]?.init(activity)
            offerwallOfferHashMap[offer]
        }
    }

    fun getOfferwalls(vararg offers: Offer, activity: Activity): ArrayList<BaseOfferwall<currencyType>?> {
        val list = ArrayList<BaseOfferwall<currencyType>?>()
        offers.forEach { list.add(getOfferwall(it, activity)) }
        return list
    }

    fun init(activity: Activity) =
        offerwallOfferHashMap.values.forEach { mutableEntry -> mutableEntry.init(activity) }

    fun onCreate(activity: Activity) =
        offerwallOfferHashMap.values.forEach { mutableEntry -> mutableEntry.onCreate(activity) }

    fun onResume(activity: Activity) =
        thread { offerwallOfferHashMap.values.forEach { mutableEntry -> mutableEntry.onResume(activity) } }

    fun onPause(activity: Activity) =
        thread { offerwallOfferHashMap.values.forEach { mutableEntry -> mutableEntry.onPause(activity) } }

    fun setRewardListener(listener: (coinsAmount: currencyType, type: BaseOfferwall<currencyType>, bonusReward: (() -> Unit)?) -> Unit) =
        offerwallOfferHashMap.values.forEach { mutableEntry -> mutableEntry.rewardListener = listener }

}

class OfferTypes {

    interface VideoOffer

    interface OfferWall

}