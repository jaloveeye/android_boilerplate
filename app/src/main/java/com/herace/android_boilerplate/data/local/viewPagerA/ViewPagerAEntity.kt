package com.herace.android_boilerplate.data.local.viewPagerA

import com.google.gson.annotations.SerializedName
import com.herace.android_boilerplate.util.ListItemViewModel

data class ViewPagerAEntity(
    @SerializedName("id")
    var id : Int,

    @SerializedName("text")
    var text : String

) : ListItemViewModel()