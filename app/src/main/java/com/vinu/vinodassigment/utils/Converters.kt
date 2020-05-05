package com.vinu.vinodassigment.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.vinu.vinodassigment.models.ImageModel

class Converters {
    @TypeConverter
    fun fromArrayLisr(value: List<ImageModel>?) = Gson().toJson(value)

    @TypeConverter
    fun fromString(value: String) = Gson().fromJson(value, Array<ImageModel>::class.java).toList()
}