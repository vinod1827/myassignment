package com.vinu.vinodassigment.api

import com.vinu.vinodassigment.models.FeedModel
import com.vinu.vinodassigment.models.ResponseModel
import retrofit2.http.GET

interface ApiService {
    @GET("/us/rss/newfreeapplications/limit=2/json")
    suspend fun getResponseData(): FeedModel
}