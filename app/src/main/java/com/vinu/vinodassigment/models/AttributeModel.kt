package com.vinu.vinodassigment.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AttributeModel(
    @SerializedName("im:id")
    var id: String? = null
) : Parcelable
