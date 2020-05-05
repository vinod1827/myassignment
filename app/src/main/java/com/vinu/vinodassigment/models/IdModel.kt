package com.vinu.vinodassigment.models

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IdModel(
    @Embedded
    var attributes: AttributeModel? = null
) : Parcelable
