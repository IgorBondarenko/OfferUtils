package com.offerutils

import android.content.Context
import android.provider.Settings
import org.apache.commons.lang.RandomStringUtils
import org.intellij.lang.annotations.Language
import java.util.regex.Pattern

object Utils {

    @Language("RegExp")
    fun validateEmail(email: String): Boolean = validateString(email, "^[\\w-\\+а-яА-Я]+(\\.[\\w]+)*@[\\w-а-яА-Я]+(\\.[\\w]+а-яА-Я)*(\\.[a-zA-Zа-яА-Я]{2,})$")

    @Language("RegExp")
    fun validateWallet(wallet: String): Boolean = validateString(wallet, "^[0-9a-zA-Z]{5,}\$")

    @Language("RegExp")
    fun validateName(name: String): Boolean = validateString(name, "^[0-9a-zA-Z]{3,}\$")

    @Language("RegExp")
    fun validateVisa(wallet: String) = validateString(wallet, "[4]+[0-9]{12,18}$")

    @Language("RegExp")
    fun validateWM(wallet: String) = validateString(wallet, "[Z]+[0-9]{12}$]")

    fun validateInvite(code: String, pattern: String) = validateString(code, pattern)

    fun getUserID(context: Context): String =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    fun getApplicationName(context: Context): CharSequence? = context.applicationInfo.loadLabel(context.packageManager)

    fun generateCode(length: Int, toUpperCase: Boolean = true): String = RandomStringUtils
        .randomAlphanumeric(length)
        .apply { if (toUpperCase) toUpperCase() }

    private fun validateString(string: String, regex: String): Boolean = Pattern
        .compile(regex)
        .matcher(string)
        .matches()

}