package com.vinu.vinodassigment.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "response_data")
data class ResponseDataModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "primaryId")
    var primaryId: Int = 0,
    @Embedded
    var feed: FeedModel? = null
) : Parcelable