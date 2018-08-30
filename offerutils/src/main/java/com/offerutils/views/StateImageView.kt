package com.offerutils.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import com.offerutils.R

class StateImageView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {

    init {
        initAttrs(context, attrs)
    }

    var stateSelected: Drawable? = null
        set(value) {
            field = value
            updateDrawableStates()
        }
    var statePressed: Drawable? = null
        set(value) {
            field = value
            updateDrawableStates()
        }
    var stateDisabled: Drawable? = null
        set(value) {
            field = value
            updateDrawableStates()
        }

    private fun initAttrs(context: Context, attrs: AttributeSet) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.StateImageView) ?: return

        stateSelected = attributes.getDrawable(R.styleable.StateImageView_state_selected)
        statePressed = attributes.getDrawable(R.styleable.StateImageView_state_pressed)
        stateDisabled = attributes.getDrawable(R.styleable.StateImageView_state_disabled)

        attributes.recycle()
    }

    private fun updateDrawableStates() {
        with(StateListDrawable()) {
            addState(intArrayOf(android.R.attr.state_pressed), statePressed)
            addState(intArrayOf(android.R.attr.state_selected), stateSelected)
            addState(intArrayOf(-android.R.attr.state_enabled), stateDisabled)
            addState(intArrayOf(), this@StateImageView.drawable)
            setImageDrawable(this)
        }
    }

}