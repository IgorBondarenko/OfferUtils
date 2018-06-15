package com.offerutils.managers

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.TextView
import com.offerutils.R
import com.offerutils.extensions.setAnimationListener

class AnimationManager constructor(val context: Context) {

    fun changeText(target: TextView?, text: String, direction: Direction) {

        val show = getAnimation(if (direction == Direction.TOP_TO_BOTTOM) R.anim.slide_in_top_change else R.anim.slide_in_bottom_change)
        val hide = getAnimation(if (direction == Direction.TOP_TO_BOTTOM) R.anim.slide_out_bottom_change else R.anim.slide_out_top_change)

        hide.setAnimationListener(onAnimationFinishListener = {
            target?.visibility = View.INVISIBLE

            show.setAnimationListener(onAnimationStartListener = {
                target?.text = text
                target?.visibility = View.VISIBLE
            })
            target?.startAnimation(show)
        })
        target?.startAnimation(hide)
    }

    fun pressWithBounce(view: View, onAnimationFinish: () -> Unit) {
        with(getAnimation(R.anim.press_with_bounce)) {
            setAnimationListener(onAnimationFinishListener = onAnimationFinish::invoke)
            view.startAnimation(this)
        }
    }

    fun bounceElement(view: View) {
        with(getAnimation(R.anim.bounce_element)) {
            setAnimationListener(onAnimationFinishListener = { view.isSelected = true })
            view.startAnimation(this)
        }
    }

    fun transition(activity: Activity, targetView: View, transitionName: String, intent: Intent, request: Int = -1) {
        val options: ActivityOptionsCompat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, targetView, transitionName)
        } else {
            ActivityOptionsCompat.makeScaleUpAnimation(targetView, targetView.x.toInt(), targetView.y.toInt(), targetView.width, targetView.height)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activity.startActivityForResult(intent, request, options.toBundle())
        } else {
            activity.startActivityForResult(intent, request)
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun showCircularRevealAnimation(view: View, content: View, duration: Long) {

        with(ViewAnimationUtils.createCircularReveal(view, view.width / 2, view.height / 2, 0f, 1000f)) {
            this.duration = duration
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    view.visibility = View.VISIBLE
                }

                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)

                    with(getAnimation(android.R.anim.fade_out)){
                        setAnimationListener(
                            onAnimationStartListener = { content.visibility = View.VISIBLE },
                            onAnimationFinishListener = { view.visibility = View.GONE }
                        )
                        setDuration(350)
                        view.startAnimation(this)
                    }
                }
            })
            start()
        }
    }

    fun getAnimation(anim: Int): Animation = AnimationUtils.loadAnimation(context, anim)

    fun getFlipAnimation(fromYValue: Float, toYValue: Float, speed: Long): Animation {
        val animation = TranslateAnimation(
            Animation.RELATIVE_TO_PARENT, 0.0f,
            Animation.RELATIVE_TO_PARENT, 0.0f,
            Animation.RELATIVE_TO_PARENT, fromYValue, //1.0f
            Animation.RELATIVE_TO_PARENT, toYValue)
        animation.interpolator = AccelerateInterpolator()
        animation.duration = speed
        return animation
    }

    enum class Direction {
        TOP_TO_BOTTOM, BOTTOM_TO_TOP
    }

}