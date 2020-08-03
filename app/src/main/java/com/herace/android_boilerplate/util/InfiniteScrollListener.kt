package com.herace.android_boilerplate.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InfiniteScrollListener(val func: () -> Unit, private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    private val threshold = 1

    private var previousTotal = 0
    private var loading = true
    private var visibleThreshold = threshold
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    fun reset() {
        previousTotal = 0
        loading = true
        visibleThreshold = threshold
        firstVisibleItem = 0
        visibleItemCount = 0
        totalItemCount = 0
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if (loading) {
                if (totalItemCount != previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }

            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                // End has been reached
                println("InfiniteScrollListener2 call func")
                loading = true
                func()
            }
        }
    }
}