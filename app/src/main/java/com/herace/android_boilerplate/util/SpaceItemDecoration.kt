package com.herace.android_boilerplate.util

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber
import java.lang.Exception

class SpaceItemDecoration(context: Context, spaceMargin: Float ) : RecyclerView.ItemDecoration() {

    private var spacing: Int = 0
    init {
        spacing = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, spaceMargin, context.resources.displayMetrics).toInt()
    }
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        try {
            val maxCount = parent.adapter!!.itemCount
            val position = parent.getChildAdapterPosition(view)

            println("SpaceItemDecoration ${maxCount} || ${position}")
            if (maxCount == position + 1) {
                outRect.bottom = spacing
            }
        } catch (e: Exception) {
            Timber.e(e.message.toString())
        }
    }
}