package com.herace.android_boilerplate.ui.a

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber
import java.lang.Exception

class ItemDecoration(fragment: Fragment, count: Int, spacingValue: Float, outerMaginValue: Float, status : Boolean ) : RecyclerView.ItemDecoration() {

    private var spanCount: Int = 0
    private var spacing: Int = 0
    private var outerMargin: Int = 0
    private var rightMargin: Int = 0
    private var status: Boolean = status
    private var outerMargin2: Int = 0
    init {
        spanCount = count
        spacing = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, spacingValue, fragment.resources.displayMetrics).toInt()
        outerMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, outerMaginValue, fragment.resources.displayMetrics).toInt()
        outerMargin2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 33.0F, fragment.resources.displayMetrics).toInt()
        rightMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -8.0F, fragment.resources.displayMetrics).toInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
//        super.getItemOffsets(outRect, view, parent, state)

        try {
            val maxCount = parent.adapter!!.itemCount
            val position = parent.getChildAdapterPosition(view)
            val column = position % spanCount
            val row = position / spanCount
            val lastRow = (maxCount - 1) / spanCount

            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount
            outRect.top = spacing


            if (row == lastRow) {
                if (status) {
                    outRect.bottom = outerMargin
                } else {
                    outRect.bottom = outerMargin2
                }
            }
        } catch (e: Exception) {
            Timber.e(e.message.toString())
        }
    }
}