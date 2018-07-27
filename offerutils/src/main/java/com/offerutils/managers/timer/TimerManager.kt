package com.offerutils.managers.timer

import android.os.SystemClock
import com.offerutils.extensions.launchInUIWithDelay
import com.offerutils.managers.DataManager
import kotlinx.coroutines.experimental.Job

class TimerManager(val dataManager: DataManager, val type: DataManager.Type, var delay: Long) {

    val isTimerStopped: Boolean
        get() = dataManager.getData(type, 0L) == 0L

    val isTimerFinished: Boolean
        get() = leftTime < 0

    val leftTime: Long
        get() = (startTime + delay) - SystemClock.elapsedRealtime()

    private val startTime: Long
        get() = dataManager.getData(type.name, 0L)

    private var job: Job? = null

    fun getLeftTimeCounter(withSeconds: Boolean = true): String =
        with(leftTime / 1000) {
            val hours = (this / 3600).toInt()
            val minutes = ((this - hours * 3600) / 60).toInt()
            val seconds = (this - (hours * 3600).toLong() - (minutes * 60).toLong()).toInt()
            "${if(hours > 0) "$hours:" else ""}${(if (minutes < 10) "0$minutes" else minutes)}" + if (withSeconds) ":${if (seconds < 10) "0$seconds" else seconds}" else ""
        }


    fun getLeftTimeInPercents(total: Long): Int = ((leftTime * 100) / total).toInt()

    fun setElapsedStartTime() {
        dataManager.saveData(type, SystemClock.elapsedRealtime())
    }

    fun checkOverTime(overTimeListener: () -> Unit) {
        if (startTime > SystemClock.elapsedRealtime() + delay) {
            overTimeListener.invoke()
        }
    }

    fun checkTimerState(onWorkListener: suspend (job: Job?) -> Unit, onStopListener: () -> Unit) {
        when {
            leftTime > 0 -> {
                job = launchInUIWithDelay(1000) {
                    onWorkListener.invoke(job)
                }
            }
            else -> {
                resetStartTime()
                onStopListener.invoke()
            }
        }
    }

    private fun resetStartTime() = dataManager.saveData(type, 0L)

    fun stopJob() {
        job?.cancel()
    }

}