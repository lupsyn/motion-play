package it.enrico.animationtest

import android.os.Bundle
import android.support.constraint.ConstraintLayout
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
