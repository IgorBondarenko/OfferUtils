package com.offerutils.offertoro_manager

import com.offerutils.managers.offers.BaseOfferwall

class OfferToroManagerFloat(appId: String, key: String): BaseOfferToroManager<Float>(appId, key){

    override var rewardListener: ((coinsAmount: Float, type: BaseOfferwall<Float>, bonusReward: (() -> Unit)?) -> Unit)? = null

    override val onReward: (reward: Double) -> Unit = {
        rewardListener?.invoke(it.toFloat(), this@OfferToroManagerFloat, bonusReward)
    }

}