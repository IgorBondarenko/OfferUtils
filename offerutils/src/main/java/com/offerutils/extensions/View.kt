package com.offerutils.extensions

import android.animation.LayoutTransition
import android.view.View
import android.widget.LinearLayout

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun LinearLayout.enableLayoutTransition(){
    with(LayoutTransition()){
        enableTransitionType(LayoutTransition.CHANGING)
        layoutTransition = this
    }
}
