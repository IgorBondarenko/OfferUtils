package com.offerutils.extensions

import android.animation.Animator
import android.view.ViewPropertyAnimator
import android.view.animation.Animation

fun Animation.setAnimationListener(onAnimationStartListener: (() -> Unit)? = null, onAnimationFinishListener: (() -> Unit)? = null, onAnimationRepeatListener: (() -> Unit)? = null) {

    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(p0: Animation?) {
            onAnimationRepeatListener?.invoke()
        }

        override fun onAnimationEnd(p0: Animation?) {
            onAnimationFinishListener?.invoke()
        }

        override fun onAnimationStart(p0: Animation?) {
            onAnimationStartListener?.invoke()
        }
    })

}

fun Animator.addListener(onAnimationFinishListener: (() -> Unit)? = null) {

    addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {}

        override fun onAnimationEnd(p0: Animator?) {
            onAnimationFinishListener?.invoke()
        }

        override fun onAnimationCancel(p0: Animator?) {}

        override fun onAnimationStart(p0: Animator?) {}

    })

}

fun ViewPropertyAnimator.setListener(onAnimationStartListener: (() -> Unit)? = null, onAnimationFinishListener: (() -> Unit)? = null) {
    this.setListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {}

        override fun onAnimationEnd(p0: Animator?) {
            onAnimationFinishListener?.invoke()
        }

        override fun onAnimationStart(p0: Animator?) {
            onAnimationStartListener?.invoke()
        }

        override fun onAnimationCancel(p0: Animator?) {}

    })
}
