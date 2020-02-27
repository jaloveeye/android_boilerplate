package com.herace.android_boilerplate.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.herace.android_boilerplate.data.local.fragmentA.FragmentAEntity
import com.herace.android_boilerplate.ui.a.FragmentARecyclerAdapter

object FragmentABindingAdapter {
    @JvmStatic
    @BindingAdapter("fragmentAAdapter")
    fun setCategoryAdapter(recyclerView: RecyclerView, items: List<FragmentAEntity>) {
        if(recyclerView.adapter != null) {
            val adapter : FragmentARecyclerAdapter = recyclerView.adapter as FragmentARecyclerAdapter
            adapter.updateList(items)
        }
    }
}