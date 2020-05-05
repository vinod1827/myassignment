package com.vinu.vinodassigment.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vinu.vinodassigment.models.FeedModel
import com.vinu.vinodassigment.models.ResponseDataModel
import com.vinu.vinodassigment.models.ResponseModel

@Dao
interface ResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResponse(feedModel: ResponseDataModel)

    @Query("SELECT * FROM response_data")
    fun getAllResponseData(): ResponseDataModel?
}