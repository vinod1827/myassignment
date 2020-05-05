package com.vinu.vinodassigment.repository

import androidx.lifecycle.MutableLiveData
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.google.gson.Gson
import com.vinu.vinodassigment.dao.ResponseDao
import com.vinu.vinodassigment.models.ResponseDataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch

class ResponseRepository(
    private val responseDao: ResponseDao
) {

    var responseModelData = MutableLiveData<ResponseDataModel>()

    fun getData() {
        AndroidNetworking.get("https://itunes.apple.com/us/rss/newfreeapplications/limit=2/json")
            .setPriority(Priority.LOW)
            .build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    val responseData = Gson().fromJson(response, ResponseDataModel::class.java)
                    CoroutineScope(Default).launch {
                        responseData?.let {
                            responseDao.insertResponse(responseData)
                        }
                    }
                    responseModelData.postValue(responseData)
                }

                override fun onError(anError: ANError?) {
                    responseModelData.postValue(null)
                }

            })
    }
}