package com.herace.android_boilerplate.data.local.temp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "temp")
data class TempEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int?,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title : String?,

    @ColumnInfo(name = "count")
    @SerializedName("count")
    var count: Int?
) {

}