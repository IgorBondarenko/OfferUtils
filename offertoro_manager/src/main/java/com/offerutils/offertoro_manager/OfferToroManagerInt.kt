package com.offerutils.offertoro_manager

import com.offerutils.managers.offers.BaseOfferwall

class OfferToroManagerInt(appId: String, key: String): BaseOfferToroManager<Int>(appId, key){

    override var rewardListener: ((coinsAmount: Int, type: BaseOfferwall<Int>, bonusReward: (() -> Unit)?) -> Unit)? = null

    override val onReward: (reward: Double) -> Unit = {
        rewardListener?.invoke(it.toInt(), this@OfferToroManagerInt, bonusReward)
    }

}