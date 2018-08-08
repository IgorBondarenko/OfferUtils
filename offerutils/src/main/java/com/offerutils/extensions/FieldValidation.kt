package com.offerutils.extensions

import android.support.design.widget.TextInputLayout
import android.widget.EditText
import com.offerutils.utils.ValidationUtils as validationUtil


fun TextInputLayout.setupEditText() {
    editText?.addTextChangedListener(beforeTextChanged = {
        error = null
        isErrorEnabled = false
    })
}

fun TextInputLayout.validateEmail(): Boolean =
    this.validateField(validationUtil.validateEmail(getString()), "email")

fun EditText.validateEmail(): Boolean =
    this.validateField(validationUtil.validateEmail(stringValue), "email")

fun TextInputLayout.validateDefaultWallet(): Boolean =
    this.validateField(validationUtil.validateWallet(getString()), "wallet")

fun EditText.validateDefaultWallet(): Boolean =
    this.validateField(validationUtil.validateWallet(stringValue), "wallet")

fun TextInputLayout.validateName(): Boolean =
    this.validateField(validationUtil.validateName(getString()), "name")

fun EditText.validateName(): Boolean =
    this.validateField(validationUtil.validateName(stringValue), "name")

fun TextInputLayout.validateVisaNumber(): Boolean =
    this.validateField(validationUtil.validateString(getString(), validationUtil.regexVisaCard), "card number")

fun EditText.validateVisaNumber(): Boolean =
    this.validateField(validationUtil.validateString(stringValue, validationUtil.regexVisaCard), "card number")

fun TextInputLayout.validateField(validation: Boolean, fieldType: String): Boolean {
    val showError: (error: String) -> Boolean = {
        this.error = it
        requestFocus()
        false
    }

    return when {
        getString().isEmpty() -> showError("Empty $fieldType")
        !validation -> showError("Incorrect $fieldType")
        else -> true
    }
}

fun EditText.validateField(validation: Boolean, fieldType: String): Boolean {
    val showError: (error: String) -> Boolean = {
        this.error = it
        requestFocus()
        false
    }

    return when {
        stringValue.isEmpty() -> showError("Empty $fieldType")
        !validation -> showError("Incorrect $fieldType")
        else -> true
    }
}


private fun TextInputLayout.getString() = editText?.stringValue ?: ""

