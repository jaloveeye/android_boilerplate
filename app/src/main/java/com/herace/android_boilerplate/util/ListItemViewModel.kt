package com.herace.android_boilerplate.util

abstract class ListItemViewModel{
    var adapterPosition: Int = -1
    var onListItemViewClickListener: GenericAdapter.OnListItemViewClickListener? = null
}