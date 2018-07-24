package com.offerutils.extensions

import android.support.design.widget.TextInputLayout

fun TextInputLayout.setupEditText(){
    editText?.addTextChangedListener(beforeTextChanged = {
        error = null
        isErrorEnabled = false
    })
}

fun TextInputLayout.validateWallet(): Boolean = this.validateField(com.offerutils.Utils.validateWallet(getString()), "wallet")

fun TextInputLayout.validateName(): Boolean = this.validateField(com.offerutils.Utils.validateName(getString()), "name")

fun TextInputLayout.validateVisaNumber(): Boolean = this.validateField(com.offerutils.Utils.validateVisa(getString()), "card number")

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

private fun TextInputLayout.getString() = editText?.textString ?: ""

