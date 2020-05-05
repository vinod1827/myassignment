package com.vinu.vinodassigment.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.vinu.vinodassigment.models.ImageModel
import com.vinu.vinodassigment.models.ResponseModel

class EntryConverters {
    @TypeConverter
    fun fromArrayLisr(value: List<ResponseModel>?) = Gson().toJson(value)

    @TypeConverter
    fun fromString(value: String) = Gson().fromJson(value, Array<ResponseModel>::class.java).toList()
}