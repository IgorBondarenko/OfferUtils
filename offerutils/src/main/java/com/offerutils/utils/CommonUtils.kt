package com.offerutils.utils

import android.content.Context
import android.provider.Settings
import org.apache.commons.lang.RandomStringUtils

object CommonUtils {

    fun getUserID(context: Context): String =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    fun getApplicationName(context: Context): CharSequence? =
        context.applicationInfo.loadLabel(context.packageManager)

    fun generateCode(length: Int, toUpperCase: Boolean = true): String =
        RandomStringUtils.randomAlphanumeric(length)
        .apply {
            if(toUpperCase) toUpperCase()
        }

}