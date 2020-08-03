package com.herace.android_boilerplate.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.herace.android_boilerplate.ui.base.Commons


class GenericAdapter<T : ListItemViewModel>(@LayoutRes val layoutId: Int ) :
    RecyclerView.Adapter<GenericAdapter.GenericViewHolder<T>>() {

    private val commons = Commons()
    var items = arrayListOf<T>()
    private var inflater: LayoutInflater? = null
    private var onListItemViewClickListener: OnListItemViewClickListener? = null

    fun addItems(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun updateItems(items: ArrayList<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun updateList(itemList: ArrayList<T>) {
        items.addAll(itemList)
        notifyDataSetChanged()
    }

    fun setOnListItemViewClickListener(onListItemViewClickListener: OnListItemViewClickListener){
        this.onListItemViewClickListener = onListItemViewClickListener
    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val layoutInflater = inflater ?: LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, layoutId, parent, false)
         
        return GenericViewHolder(
            binding , commons
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GenericViewHolder<T>,  position: Int) {
        val itemViewModel = items[position]
        itemViewModel.adapterPosition = position
        onListItemViewClickListener?.let { itemViewModel.onListItemViewClickListener = it }

        holder.bind(itemViewModel)
    }


    class GenericViewHolder<T : ListItemViewModel>(private val binding: ViewDataBinding, private val commons: Commons) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemViewModel: T) {

            binding.setVariable(BR.data , itemViewModel)
//            binding.setVariable(BR.common , commons)
            binding.executePendingBindings()
        }
    }

    interface OnListItemViewClickListener{
         fun onClick(view: View, position: Int , viewName : String, data : Any)
    }
}