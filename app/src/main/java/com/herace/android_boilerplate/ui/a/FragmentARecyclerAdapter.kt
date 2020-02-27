package com.herace.android_boilerplate.ui.a

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.herace.android_boilerplate.data.local.fragmentA.FragmentAEntity
import com.herace.android_boilerplate.databinding.ItemEmptyBinding
import com.herace.android_boilerplate.databinding.ItemFragmentABinding
import com.herace.android_boilerplate.ui.base.BaseRecyclerAdapter
import com.herace.android_boilerplate.R

class FragmentARecyclerAdapter : BaseRecyclerAdapter<FragmentAEntity>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<FragmentAEntity> {
        return if(getItemViewType(viewType) == VIEW_TYPE_NORMAL) {
            val binding  =  ItemFragmentABinding.inflate(LayoutInflater.from(parent.context) , parent , false)
            CategoryViewHolder(binding , this)
        }else {
            val binding = ItemEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            EmptyViewHolder(binding, this)
        }
    }

    class CategoryViewHolder(
        private var itemCategoryBinding: ItemFragmentABinding,
        baseRecyclerAdapter: BaseRecyclerAdapter<FragmentAEntity>
    ) : ViewHolder<FragmentAEntity>(itemCategoryBinding.root , baseRecyclerAdapter) {

        @SuppressLint("ResourceAsColor")
        override fun populate(item: FragmentAEntity, position: Int) {
            var data = item

            itemCategoryBinding.data = data

            if(!item.title.isNullOrEmpty()) {
                itemCategoryBinding.itemButton.visibility = View.VISIBLE
            } else {
                itemCategoryBinding.itemButton.visibility = View.GONE
            }
        }
    }



    class EmptyViewHolder(
        itemEmptyBinding: ItemEmptyBinding,
        baseRecyclerAdapter: BaseRecyclerAdapter<FragmentAEntity>
    ): ViewHolder<FragmentAEntity>(itemEmptyBinding.root, baseRecyclerAdapter)

    companion object {
        private lateinit var context: Context

        fun setContext(con: Context) {
            context = con
        }

    }
}