package com.offerutils.extensions

import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

var EditText.stringValue: String
    get() =  this.text.toString()
    set(value) {
        this.setText(value, TextView.BufferType.EDITABLE)
    }

var TextInputLayout.textString: String
    get() = this.editText?.stringValue ?: ""
    set(value) {
        this.editText?.stringValue = value
    }

fun EditText.addTextChangedListener(beforeTextChanged: () -> Unit, afterTextChanged: ((editable: Editable?) -> Unit)? = null) {
    addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(p0: Editable?) {
            afterTextChanged?.invoke(p0)
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            beforeTextChanged.invoke()
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

    })
}