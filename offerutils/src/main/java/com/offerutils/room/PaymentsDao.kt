package com.offerutils.room

interface PaymentsDao<payment>{

    fun addPayment(payment: payment)

    fun getAllPayments(): MutableList<payment>

    fun changeDate(date: Long, id: Int)

}