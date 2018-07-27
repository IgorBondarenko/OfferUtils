package com.offerutils.extensions

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.util.*

fun launchInUIWithDelay(delayTime: Long, action: suspend () -> Unit): Job =
    launch(UI) {
        delay(delayTime)
        action.invoke()
    }

fun doAfterTime(day: Int, month: Int, year: Int, doBefore: (() -> Unit)? = null, doAfter: () -> Unit){
    with(Calendar.getInstance()){
        set(Calendar.DAY_OF_MONTH, day)
        set(Calendar.MONTH, month)
        set(Calendar.YEAR, year)

        if(System.currentTimeMillis() > this.timeInMillis){
            doAfter()
        } else doBefore?.invoke()
    }
}

fun Float.format(format: String = "%.2f") : String = String.format(format, this)