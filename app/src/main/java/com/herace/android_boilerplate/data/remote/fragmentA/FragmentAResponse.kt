package com.herace.android_boilerplate.data.remote.fragmentA

import com.google.gson.annotations.SerializedName
import com.herace.android_boilerplate.data.local.fragmentA.FragmentAEntity

data class FragmentAResponse (
    @field:SerializedName("resultCode")
    val resultCode : Int? = null,

    @field:SerializedName("resultMsg")
    val resultMsg : String? = null,

    @field:SerializedName("resultData")
    val resultData : FragmentAData? = null
)

data class FragmentAData(
    @field:SerializedName("data")
    val data : List<FragmentAEntity>? = null
)