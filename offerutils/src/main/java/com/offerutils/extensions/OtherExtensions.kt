package com.offerutils.extensions

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

fun launchInUIWithDelay(delayTime: Long, action: suspend () -> Unit): Job =
    launch(UI) {
        delay(delayTime)
        action.invoke()
    }