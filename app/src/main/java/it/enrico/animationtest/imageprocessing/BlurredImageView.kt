package it.enrico.animationtest.imageprocessing

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import it.enrico.animationtest.R
import it.enrico.animationtest.image.GlideApp
import jp.wasabeef.glide.transformations.BlurTransformation

class BlurredImageView : FrameLayout {

    private var background: ImageView
    private var backgroundBlurred: ImageView

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.blurred_imageview, this)

        background = findViewById(R.id.image_background)
        backgroundBlurred = findViewById(R.id.image_background_blurred)
    }

    fun setImage(resId: Int) {
        GlideApp.with(this)
                .load(resId)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(background)

        GlideApp.with(this)
                .load(resId)
                .centerCrop()
                .apply(bitmapTransform(BlurTransformation(RADIUS, SAMPLING)))
                .into(backgroundBlurred)
    }

    fun setImage(resUrl: String) {
        GlideApp.with(this)
                .load(resUrl)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(background)

        GlideApp.with(this)
                .load(resUrl)
                .centerCrop()
                .apply(bitmapTransform(BlurTransformation(RADIUS, SAMPLING)))
                .into(backgroundBlurred)
    }

    fun setBlurredPercentage(percentage: Float) {
        backgroundBlurred.alpha = percentage
    }

    companion object {
        const val RADIUS = 35
        const val SAMPLING = 3
    }

}