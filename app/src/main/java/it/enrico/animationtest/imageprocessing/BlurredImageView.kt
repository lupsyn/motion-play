package it.enrico.animationtest.imageprocessing

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import it.enrico.animationtest.R
import it.enrico.animationtest.image.GlideApp
import jp.wasabeef.glide.transformations.BlurTransformation

class BlurredImageView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var background: ImageView
    private var backgroundBlurred: ImageView

    init {
        View.inflate(context, R.layout.blurred_imageview, this)

        background = findViewById(R.id.image_background)
        backgroundBlurred = findViewById(R.id.image_background_blurred)
    }

    fun setImage(resId: Int) {
        GlideApp.with(this)
                .load(resId)
                .centerCrop()
                .into(background)

        GlideApp.with(this)
                .load(resId)
                .centerCrop()
                .apply(bitmapTransform(BlurTransformation(35, 3)))
                .into(backgroundBlurred)
    }

    fun setBlurredPercentage(percentage: Float) {
        backgroundBlurred.alpha = percentage
    }

}