package com.offerutils.offertoro_manager

import android.app.Activity
import android.util.Log
import com.offertoro.sdk.OTOfferWallSettings
import com.offertoro.sdk.interfaces.OfferWallListener
import com.offertoro.sdk.interfaces.VideoOfferListener
import com.offertoro.sdk.sdk.OffersInit
import com.offerutils.Utils
import com.offerutils.managers.offers.BaseOfferwall
import com.offerutils.managers.offers.OfferTypes

abstract class BaseOfferToroManager<currency: Number>(val appId: String, val secretKey: String): BaseOfferwall<currency>, OfferTypes.OfferWall{

    override var bonusReward: (() -> Unit)? = null

    override val name: String get() = "OfferToro"

    protected abstract val onReward: (reward: Double) -> Unit

    override fun init(activity: Activity) {

        OTOfferWallSettings.getInstance().configInit(appId, secretKey, Utils.getUserID(activity))
        OffersInit.getInstance().create(activity)

        OffersInit.getInstance().setOfferWallListener(object : OfferWallListener {
            override fun onOTOfferWallInitSuccess() {
                Log.d("offertorro1", "onOTOfferWallInitSuccess: ")
            }

            override fun onOTOfferWallInitFail(s: String) {

            }

            override fun onOTOfferWallOpened() {
                Log.d("offertorro1", "onOTOfferWallOpened: ")
            }

            override fun onOTOfferWallCredited(v: Double, v1: Double) {
                onReward(v)
//                rewardListener?.invoke(v.toFloat(), this@OfferToroManagerFloat, bonusReward)
                Log.d("offertorro1", "onOTOfferWallCredited: $v | $v1")
            }

            override fun onOTOfferWallClosed() {

            }
        })

        OffersInit.getInstance().setVideoOfferListener(object : VideoOfferListener {
            override fun onOTRVideosInitSuccess() {

            }

            override fun onOTRVideosInitFail(s: String) {

            }

            override fun onOTRVideoAvailability(b: Boolean) {

            }

            override fun onOTRVideoStart() {

            }

            override fun onOTRVideoCredited(v: Double) {
                onReward(v)
//                rewardListener?.invoke(v.toFloat(), this@OfferToroManagerFloat, bonusReward)
                Log.d("offertoro", "onOTRVideoCredited: " + v)
            }
        })

    }

    override fun show(activity: Activity): Boolean {
        Log.d("offertorro1", "show: " + OTOfferWallSettings.getInstance().configInit(appId, secretKey, Utils.getUserID(activity)).isInitialized)
        OffersInit.getInstance().showOfferWall(activity)
        return true
    }

    fun showVideo(activity: Activity) {
        Log.d("offertorro1", "showVideo: " + OTOfferWallSettings.getInstance().configInit(appId, secretKey, Utils.getUserID(activity)).isInitialized)
        OffersInit.getInstance().showVideoOffer(activity)
    }

}