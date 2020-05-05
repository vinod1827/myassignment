package com.vinu.vinodassigment.models

import android.os.Parcelable
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseModel(

    @Embedded
    @SerializedName("id")
    var idModel: IdModel? = null,

    @Embedded
    @SerializedName("im:image")
    var images: ArrayList<ImageModel>? = null,

    @Embedded
    @SerializedName("im:artist")
    var artistModel: ArtistModel? = null,

    @Embedded
    @SerializedName("title") var title: TitleModel? = null
) : Parcelable
