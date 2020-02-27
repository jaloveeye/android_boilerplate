package com.herace.android_boilerplate.data.remote.temp

import com.google.gson.annotations.SerializedName

data class TempResponse (
    @field:SerializedName("resultCode")
    val resultCode : Int? = null,

    @field:SerializedName("resultMsg")
    val resultMsg : String? = null,

    @field:SerializedName("resultData")
    val resultData : TempData? = null
)


data class TempData(
    @field:SerializedName("id")
    val id : Int?,

    @field:SerializedName("title")
    val title : String? = null,

    @field:SerializedName("count")
    val count : Int?
)