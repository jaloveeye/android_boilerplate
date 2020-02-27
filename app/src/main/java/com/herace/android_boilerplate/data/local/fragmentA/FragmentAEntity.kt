package com.herace.android_boilerplate.data.local.fragmentA

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "fragmentA")
class FragmentAEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int?,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title : String?,

    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content : String?,

    @ColumnInfo(name = "displayOrder")
    @SerializedName("displayOrder")
    val displayOrder : Int?,

    @ColumnInfo(name = "warning")
    @SerializedName("warning")
    val warning : Boolean?,

    @ColumnInfo(name = "inspection")
    @SerializedName("inspection")
    val inspection : Boolean?
)