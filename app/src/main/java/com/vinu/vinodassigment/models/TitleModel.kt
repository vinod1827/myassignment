package com.vinu.vinodassigment.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TitleModel(
    @ColumnInfo(name = "title_label")
    var label : String = "") : Parcelable
