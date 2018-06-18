package com.offerutils

import android.app.Application
import android.util.Log
import com.orhanobut.hawk.Hawk

open class UtilsApplication: Application(){

    override fun onCreate() {
        super.onCreate()

        Log.d("CURRENT_PACKAGE", "${applicationContext?.packageName}")

        Hawk.init(this).build()
    }

}