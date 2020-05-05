package com.vinu.vinodassigment.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FeedModel(
    @SerializedName("entry")
    @Expose
    var entry: List<ResponseModel>? = null
) : Parcelable