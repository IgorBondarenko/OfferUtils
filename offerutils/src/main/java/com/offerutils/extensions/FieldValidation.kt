package com.offerutils.extensions

import android.support.design.widget.TextInputLayout
import android.widget.EditText

fun TextInputLayout.setupEditText(){
    editText?.addTextChangedListener(beforeTextChanged = {
        error = null
        isErrorEnabled = false
    })
}

fun TextInputLayout.validateEmail(): Boolean = this.validateField(com.offerutils.Utils.validateEmail(getString()), "email")

fun EditText.validateEmail(): Boolean = this.validateField(com.offerutils.Utils.validateEmail(textString), "email")

fun TextInputLayout.validateWallet(): Boolean = this.validateField(com.offerutils.Utils.validateWallet(getString()), "wallet")

fun EditText.validateWallet(): Boolean = this.validateField(com.offerutils.Utils.validateWallet(textString), "wallet")

fun TextInputLayout.validateName(): Boolean = this.validateField(com.offerutils.Utils.validateName(getString()), "name")

fun EditText.validateName(): Boolean = this.validateField(com.offerutils.Utils.validateName(textString), "name")

fun TextInputLayout.validateVisaNumber(): Boolean = this.validateField(com.offerutils.Utils.validateVisa(getString()), "card number")

fun EditText.validateVisaNumber(): Boolean = this.validateField(com.offerutils.Utils.validateVisa(textString), "card number")

private fun TextInputLayout.validateField(validation: Boolean, fieldType: String): Boolean = when {
    getString().isEmpty() -> {
        error = "Empty $fieldType"
        requestFocus()
        false
    }
    !validation -> {
        error = "Incorrect $fieldType"
        requestFocus()
        false
    }
    else -> true
}

private fun EditText.validateField(validation: Boolean, fieldType: String): Boolean = when {
    textString.isEmpty() -> {
        error = "Empty $fieldType"
        requestFocus()
        false
    }
    !validation -> {
        error = "Incorrect $fieldType"
        requestFocus()
        false
    }
    else -> true
}


private fun TextInputLayout.getString() = editText?.textString ?: ""

