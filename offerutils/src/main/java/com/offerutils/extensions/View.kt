package com.offerutils.extensions

import android.animation.LayoutTransition
import android.graphics.Paint
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun ViewGroup.enableLayoutTransition(){
    with(LayoutTransition()){
        enableTransitionType(LayoutTransition.CHANGING)
        layoutTransition = this
    }
}

fun ViewGroup.inflateLayout(@LayoutRes layoutRes: Int): View = LayoutInflater.from(context).inflate(layoutRes, this, false)

fun TextView.underLine() {
    paint.flags = paint.flags or Paint.UNDERLINE_TEXT_FLAG
    paint.isAntiAlias = true
}

