package com.offerutils.mvp.models

import com.offerutils.room.PaymentsDao
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlinx.coroutines.experimental.runBlocking

interface PaymentsModel<payment> {

    val paymentsDao: PaymentsDao<payment>

    fun saveWithdraw(payment: payment) =
        launch(newSingleThreadContext("DataBaseThread")) {
            paymentsDao.addPayment(payment)
        }

    fun getPayments(): List<payment> =
        runBlocking(newSingleThreadContext("DataBaseThread")) {
            paymentsDao.getAllPayments()
        }

    fun changeDateOfPayment(itemId: Int) =
        runBlocking(newSingleThreadContext("DataBaseThread")) {
            paymentsDao.changeDate(System.currentTimeMillis(), itemId)
        }

}