package com.offerutils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

fun EditText.getString(): String = this.text.toString()

fun EditText.setString(text: String) = this.setText(text, TextView.BufferType.EDITABLE)

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