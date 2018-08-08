package com.offerutils.utils

import org.intellij.lang.annotations.Language
import java.util.regex.Pattern

object ValidationUtils{

    @Language("RegExp")
    val regexEthereumWallet = "^0x[A-Fa-f0-9]{40}$"

    @Language("RegExp")
    val regexBitcoinWallet = "^([13][a-km-zA-HJ-NP-Z1-9]{25,34})"

    @Language("RegExp")
    val regexWebMoneyWallet = "[Z]+[0-9]{12}$]"

    @Language("RegExp")
    val regexVisaCard = "[4]+[0-9]{12,18}$"

    @Language("RegExp")
    private val regexDefaultWallet = "^[0-9a-zA-Z]{5,}\$"

    @Language("RegExp")
    fun validateEmail(email: String): Boolean =
        validateString(email, "^[\\w-+а-яА-Я]+(\\.[\\w]+)*@[\\w-а-яА-Я]+(\\.[\\w]+а-яА-Я)*(\\.[a-zA-Zа-яА-Я]{2,})$")

    @Language("RegExp")
    fun validateWallet(wallet: String, regex: String = regexDefaultWallet): Boolean =
        validateString(wallet, regex)

    @Language("RegExp")
    fun validateName(name: String): Boolean =
        validateString(name, "^[0-9a-zA-Z]{3,}\$")

    fun validateString(string: String, regex: String): Boolean = Pattern
        .compile(regex)
        .matcher(string)
        .matches()

}