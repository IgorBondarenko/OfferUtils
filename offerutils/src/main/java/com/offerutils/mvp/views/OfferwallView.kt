package com.offerutils.mvp.views

import android.app.Activity
import com.offerutils.extensions.whenFalse
import com.offerutils.extensions.showLongToast
import com.offerutils.managers.offers.OfferTypes
import com.offerutils.managers.offers.OfferwallManager

interface OfferwallView<currencyType : Number> {

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

    fun Activity.showVideoRotation(bonusReward: (() -> Unit)? = null, rotationOffers: Array<OfferwallManager.Offer>) {
        checkOffers(bonusReward, *rotationOffers)
    }

    private fun Activity.showOffer(offerName: OfferwallManager.Offer, bonusReward: (() -> Unit)? = null): Pair<Boolean, Boolean> =
        with(offerwallManager.getOfferwall(offerName, this)) {
            val isOfferReady = this?.let {
                if (it is OfferTypes.VideoOffer) it.bonusReward = bonusReward
                it.show(this@showOffer)
            } ?: false
            Pair(isOfferReady, this is OfferTypes.VideoOffer)
        }


    private fun Activity.showMultipleOffers(bonusReward: (() -> Unit)? = null, vararg offersName: OfferwallManager.Offer): Pair<Boolean, Boolean> =
        offersName.forEach {
            val pair = showOffer(it, bonusReward)
            if (pair.first) return Pair(true, pair.second)
        }.let { Pair(false, true) }

    private fun Activity.checkOffer(state: Boolean, isVideoOffer: Boolean) =
        state.apply { if (!this) showLongToast("There are no ${if (isVideoOffer) "video" else "offers"} available") }

}