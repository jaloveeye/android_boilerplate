package com.herace.android_boilerplate.util

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class SquareLayout(context : Context, attrs: AttributeSet) : LinearLayout(context , attrs) {
    override fun onMeasure(width: Int, height: Int) {
        super.onMeasure(width, width)
    }
}