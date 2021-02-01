package com.dnc.marketingapp.presentation.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.dnc.marketingapp.R
import kotlinx.android.synthetic.main.counter_view_layout.view.*

class CustomCounterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.counter_view_layout, this, true)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomCounterView, 0, 0)

            val firstImage =  typedArray.getResourceId(R.styleable.CustomCounterView_iconFirst, -1)
            val secondImage =  typedArray.getResourceId(R.styleable.CustomCounterView_iconSecond, -1)
            val thirdImage =  typedArray.getResourceId(R.styleable.CustomCounterView_iconThird, -1)
            val forthImage =  typedArray.getResourceId(R.styleable.CustomCounterView_iconFourth, -1)


            if (firstImage != -1) first_screen_iv.setImageDrawable(ContextCompat.getDrawable(context, firstImage))
            if (secondImage != -1) second_screen_iv.setImageDrawable(ContextCompat.getDrawable(context, secondImage))
            if (thirdImage != -1) third_screen_iv.setImageDrawable(ContextCompat.getDrawable(context, thirdImage))
            if (forthImage != -1) forth_screen_iv.setImageDrawable(ContextCompat.getDrawable(context, forthImage))

            typedArray.recycle()
        }
    }

    var firstImageDrawable : Int = R.drawable.ic_screen_not_selected
        set(value){
            field = value
            first_screen_iv.setImageDrawable(ContextCompat.getDrawable(context, value))
            invalidate()
        }

    var secondImageDrawable : Int = R.drawable.ic_screen_not_selected
        set(value){
            field = value
            second_screen_iv.setImageDrawable(ContextCompat.getDrawable(context, value))
            invalidate()
        }

    var thirdImageDrawable : Int = R.drawable.ic_screen_not_selected
        set(value){
            field = value
            third_screen_iv.setImageDrawable(ContextCompat.getDrawable(context, value))
            invalidate()
        }

    var forthImageDrawable : Int = R.drawable.ic_screen_not_selected
        set(value){
            field = value
            forth_screen_iv.setImageDrawable(ContextCompat.getDrawable(context, value))
            invalidate()
        }
}
