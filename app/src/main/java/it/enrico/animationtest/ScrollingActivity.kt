package it.enrico.animationtest

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import it.enrico.animationtest.imageprocessing.BlurredImageView


class ScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val motionLayout = findViewById<ConstraintLayout>(R.id.motionLayout)
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout3)
        val list = mutableListOf<String>()
        for (i in 1..100) {
            list.add("" + i)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecyclerAdapter(list)

        val w = window
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
        linearLayout.applyWindowInsets(true, true)
        recyclerView.applyWindowInsets(true, true)

        val background = findViewById<BlurredImageView>(R.id.background)
        background.setImage("https://cdn.content.tuigroup.com/adamtui/2016_9/29_9/5ed4fe2c-6aaa-40bd-805d-a690009b9850/AUS_KIT_011_16WebOriginalCompressed.jpg?i10c=img.resize(width:1080);img.crop(width:1080%2Cheight:608)")
    }

    private fun animateStatusBar() {
        var trasparentToPrimary = AnimatedColor(window.statusBarColor, ContextCompat.getColor(this, R.color.colorPrimary))
        var primaryToTransparent = AnimatedColor(ContextCompat.getColor(this, R.color.colorPrimary), ContextCompat.getColor(this, android.R.color.transparent))

        val animator = ObjectAnimator.ofFloat(0f, 1f).setDuration(1000)
        animator.addUpdateListener { animation ->
            val v = animation.animatedValue as Float

            window.statusBarColor = trasparentToPrimary.with(v)
        }
//        animator.addListener(object : AnimatorListenerAdapter() {
//            override fun onAnimationEnd(animation: Animator) {
//                val animator2 = ObjectAnimator.ofFloat(0f, 1f).setDuration(1000)
//                animator2.addUpdateListener { animation ->
//                    val v = animation.animatedValue as Float
//                    window.statusBarColor = primaryToTransparent.with(v)
//                }
//                animator2.start()
//            }
//        })
        animator.start()
    }


    private fun View.applyWindowInsets(applyTopInset: Boolean = true, applyOtherInsets: Boolean = true): Unit {
        if (applyTopInset || applyOtherInsets) {
            ViewCompat.setOnApplyWindowInsetsListener(
                    this
            ) { view, insets ->
                // Set padding for needed insets
                view.setPadding(
                        if (applyOtherInsets) insets.systemWindowInsetLeft else view.paddingLeft,
                        if (applyTopInset) insets.systemWindowInsetTop else view.paddingTop,
                        if (applyOtherInsets) insets.systemWindowInsetRight else view.paddingRight,
                        if (applyOtherInsets) insets.systemWindowInsetBottom else view.paddingBottom
                )

                // Return without consumed insets
                insets.replaceSystemWindowInsets(
                        if (applyOtherInsets) 0 else insets.systemWindowInsetLeft,
                        if (applyTopInset) 0 else insets.systemWindowInsetTop,
                        if (applyOtherInsets) 0 else insets.systemWindowInsetRight,
                        if (applyOtherInsets) 0 else insets.systemWindowInsetBottom
                )
            }
        } else {
            // Listener is not needed
            ViewCompat.setOnApplyWindowInsetsListener(this, null)
        }
    }
}
