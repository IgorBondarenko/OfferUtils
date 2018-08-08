package com.offerutils.managers.balance

import android.content.Context
import android.media.MediaPlayer
import android.support.annotation.RawRes
import android.widget.TextView
import com.offerutils.extensions.showLongToast
import com.offerutils.managers.AnimationManager
import com.offerutils.managers.DataManager

abstract class BalanceManager<currencyType : Number>(val dataManager: DataManager) {

    var currentBalance: currencyType = 0 as currencyType
        get() = dataManager.getData(DataManager.Type.COINS_BALANCE, 0 as currencyType)
        set(value) {
            checkValue(value){
                updateListener?.invoke(field, value)
                dataManager.saveData(DataManager.Type.COINS_BALANCE, value)
                field = value
            }
        }

    protected abstract val checkValue: (value: currencyType, saveValue: () -> Unit) -> Unit

    var updateListener: ((previousBalance: currencyType, value: currencyType) -> Unit)? = null

    inner class BalanceEffects {

        private lateinit var context: Context
        private var currencyName = "Coins"
        private var showToast: Boolean = true

        private var playSound: Boolean = true
        @RawRes private var soundRes = 0

        private var enableAnimation: Boolean = true
        private var animationManager: AnimationManager? = null
        private var balanceView: TextView? = null

        fun enableToast(context: Context, enable: Boolean, currencyName: String = this.currencyName) {
            this.context = context
            this.currencyName = currencyName
            showToast = enable
        }

        fun enableSound(context: Context, enable: Boolean, @RawRes soundRes: Int = 0) {
            this.context = context
            this.soundRes = soundRes
            playSound = enable
        }

        fun enableAnimations(context: Context, enable: Boolean, animationManager: AnimationManager? = null, textView: TextView? = null){
            this.context = context
            this.animationManager = animationManager
            this.balanceView = textView
            enableAnimation = enable
        }

        fun addToBalance(value: currencyType) {
            if (showToast && ::context.isInitialized) {
                context.showLongToast("You have earned $value $currencyName")
            }

            if (playSound && ::context.isInitialized) {
                if (soundRes != 0) MediaPlayer.create(context, soundRes)?.start()
            }

            if(enableAnimation){
                animationManager?.changeText(balanceView, "$currentBalance", AnimationManager.Direction.TOP_TO_BOTTOM)
            }
        }

        fun subtractFromBalance(){
            if(enableAnimation){
                animationManager?.changeText(balanceView, "$currentBalance", AnimationManager.Direction.BOTTOM_TO_TOP)
            }
        }

    }

}
