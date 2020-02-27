package com.herace.android_boilerplate.ui.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

open class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder<T>>() {

    companion object {
        const val VIEW_TYPE_EMPTY = 0
        const val VIEW_TYPE_NORMAL = 1
    }

    private var onItemClickListener : OnItemClickListener<T>? = null
    private var onItemLongClickListener : OnItemLongClickListener<T>? = null

    interface OnItemClickListener<T> {
        fun onItemClick(view: View, item: T, position: Int)
    }

    fun setOnItemClickListener(listener: (View, T, Int) -> Unit) {
        this.onItemClickListener = object: OnItemClickListener<T> {
            override fun onItemClick(view: View, item: T, position: Int) {
                listener(view, item, position)
            }
        }
    }

    fun getOnItemClickListener() : OnItemClickListener<T>? {
        return onItemClickListener
    }

    interface OnItemLongClickListener<T> {
        fun onItemLongClick(view: View, item: T, position: Int)
    }

    fun setOnItemLongClickListener(listener: (View, T, Int) -> Unit) {
        this.onItemLongClickListener = object: OnItemLongClickListener<T> {
            override fun onItemLongClick(view: View, item: T, position: Int) {
                listener(view, item, position)
            }
        }
    }

    fun getOnItemLongClickListener() : OnItemLongClickListener<T>? {
        return onItemLongClickListener
    }

    private var items = Collections.emptyList<T>()

    override fun getItemCount(): Int {
        return if(items.isEmpty()) 1 else items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(items.isEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_NORMAL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        if(getItemViewType(position) == VIEW_TYPE_NORMAL)
            holder.populate(items[position], position)
    }

    fun updateList(itemList: List<T>) {
        items = itemList
        notifyDataSetChanged()
    }

    fun getItems() : List<T> {
        return items
    }

    open class ViewHolder<T>(
        itemView: View,
        var baseRecyclerAdapter: BaseRecyclerAdapter<T>
    ): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener {
                if (adapterPosition > -1) {
                    baseRecyclerAdapter.onItemLongClickListener?.onItemLongClick(itemView, baseRecyclerAdapter.items[adapterPosition], adapterPosition)
                    true
                } else true
            }
        }

        open fun populate(item: T, position: Int) {}

        override fun onClick(v: View) {
            if (adapterPosition > -1) {
                if(  baseRecyclerAdapter.items.size > 0) {
                    baseRecyclerAdapter.onItemClickListener?.onItemClick(v, baseRecyclerAdapter.items[adapterPosition], adapterPosition)
                }
            }
        }
    }
}