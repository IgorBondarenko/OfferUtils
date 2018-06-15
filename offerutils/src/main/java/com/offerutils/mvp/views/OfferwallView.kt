package com.offerutils.mvp.views

import android.app.Activity
import com.offerutils.extensions.showLongToast
import com.offerutils.managers.offers.OfferTypes
import com.offerutils.managers.offers.OfferwallManager

interface OfferwallView<currencyType: Number> {

    val offerwallManager: OfferwallManager<currencyType>

    fun Activity.checkOffer(offerName: OfferwallManager.Offer) {
        with(showOffer(offerName)) {
            checkOffer(first, second)
        }
    }

    fun Activity.checkOffers(bonusReward: (() -> Unit)? = null, vararg offersName: OfferwallManager.Offer) {
        with(showMultipleOffers(bonusReward, *offersName)) {
            checkOffer(first, second)
        }
    }

    fun Activity.showVideoRotation(bonusReward: (() -> Unit)? = null, rotationOffers: Array<OfferwallManager.Offer>){
        checkOffers(bonusReward, *rotationOffers)
    }

    private fun Activity.showOffer(offerName: OfferwallManager.Offer, bonusReward: (() -> Unit)? = null): Pair<Boolean, Boolean> =
        offerwallManager.getOfferwall(offerName, this).let {
            val isReady: Boolean = it.apply {
                if (this is OfferTypes.VideoOffer) {
                    this.bonusReward = bonusReward
                }
            }?.show(this) ?: false
            Pair(isReady, it is OfferTypes.VideoOffer)
        }

    private fun Activity.showMultipleOffers(bonusReward: (() -> Unit)? = null, vararg offersName: OfferwallManager.Offer): Pair<Boolean, Boolean> =
        offersName.forEach {
            val pair = showOffer(it, bonusReward)
            if (pair.first) return Pair(true, pair.second)
        }.let { Pair(false, true) }

    private fun Activity.checkOffer(state: Boolean, isVideoOffer: Boolean) =
        state.apply { if (!this) showLongToast("There are no ${if (isVideoOffer) "video" else "offers"} available") }

}