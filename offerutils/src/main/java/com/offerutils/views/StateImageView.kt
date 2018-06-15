package com.offerutils.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.widget.ImageView
import com.offerutils.R

class StateImageView: ImageView {

    private var stateDefault: Drawable? = null
    var stateSelected: Drawable? = null
    var statePressed: Drawable? = null
    var stateDisabled: Drawable? = null

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        initViews(context, attrs)
    }

    private fun initViews(context: Context, attrs: AttributeSet) {
        initAttrs(context, attrs)
        stateDefault = this.drawable
        updateDrawableStates()
    }

    private fun initAttrs(context: Context, attrs: AttributeSet) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.StateImageView) ?: return

        stateSelected = attributes.getDrawable(R.styleable.StateImageView_state_selected)
        statePressed = attributes.getDrawable(R.styleable.StateImageView_state_pressed)
        stateDisabled = attributes.getDrawable(R.styleable.StateImageView_state_disabled)

        attributes.recycle()
    }

    private fun updateDrawableStates(){
        with(StateListDrawable()){
            addState(intArrayOf(android.R.attr.state_pressed), statePressed)
            addState(intArrayOf(android.R.attr.state_selected), stateSelected)
            addState(intArrayOf(-android.R.attr.state_enabled), stateDisabled)
            setImageDrawable(this)
        }
    }

}