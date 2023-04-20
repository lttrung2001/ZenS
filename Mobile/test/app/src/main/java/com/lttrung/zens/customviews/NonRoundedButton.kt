package com.lttrung.zens.customviews

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import com.lttrung.zens.R

class NonRoundedButton(
    context: Context,
    attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatButton(context, attrs) {
    private var solidColor = Color.WHITE

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NonRoundedButton)
        solidColor = typedArray.getColor(R.styleable.NonRoundedButton_solidColor, Color.WHITE)
        typedArray.recycle()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        setBackgroundColor(solidColor)
        super.onDraw(canvas)
    }
}