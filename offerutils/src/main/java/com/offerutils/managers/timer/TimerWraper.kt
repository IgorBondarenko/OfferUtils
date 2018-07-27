package com.offerutils.managers.timer

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * @param timerManager - instance of TimerManager
 * @param doOnFirstLaunch - action on timer launch (execute one time; for example: schedule notification)
 * @param doOnStart - action on timer start (for example: prepare view to show left time)
 * @param doOnRepeat - action on every tick of timer (for example: set time on view)
 * @param doOnFinish - action when timer finished its work
 */
class TimerWrapper(
    val timerManager: TimerManager,
    val doOnFirstLaunch: () -> Unit,
    val doOnStart: () -> Unit,
    val doOnRepeat: (leftTime: String, leftTimeLong: Long, leftTimePercents: Int) -> Unit,
    val doOnFinish: () -> Unit) {

    var leftTimeWithSeconds = true

    private var timerCoroutine: Job? = null

    /**
     * call it onStart() of activity
     */
    fun start() {

        if (timerManager.isTimerStopped) {
            timerManager.setElapsedStartTime()
            doOnFirstLaunch.invoke()
        }

        doOnStart.invoke()

        if (timerCoroutine?.isActive != true) {
            timerCoroutine = launch(UI) {
                timerCoroutine()
            }
        }

    }

    /**
     * call it onPause() of activity
     */
    fun stop() {
        timerManager.stopJob()
    }

    private fun timerCoroutine() {
        doOnRepeat.invoke(timerManager.getLeftTimeCounter(leftTimeWithSeconds), timerManager.leftTime, timerManager.getLeftTimeInPercents(timerManager.delay))
        timerManager.checkTimerState(
            onWorkListener = {
                timerCoroutine = it
                timerCoroutine()
            },
            onStopListener = {
                doOnFinish.invoke()
            }
        )
    }

}