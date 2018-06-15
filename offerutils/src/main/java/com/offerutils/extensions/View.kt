package com.offerutils.extensions

import android.view.View

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}