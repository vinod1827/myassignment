package com.vinu.vinodassigment.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vinu.vinodassigment.database.FeedRoomDatabase
import com.vinu.vinodassigment.models.ResponseDataModel
import com.vinu.vinodassigment.repository.ResponseRepository

class ResponseViewModel(application: Application) :
    AndroidViewModel(application) {

    private var repository: ResponseRepository
    val responseModel: LiveData<ResponseDataModel> = MutableLiveData()

    /*
     * Name : init block
     *  Description : 1. getting instance of  data access object
     *                2. creating an instance of repository
     *                3. observing variables / data from the repository
     */
    init {
        val newsDao = FeedRoomDatabase.getDatabase(getApplication()).newsDao()
        repository = ResponseRepository(newsDao)
        repository.responseModelData.observeForever { responseData ->
            (responseModel as MutableLiveData).value = responseData
        }
    }


    /*
     * Name : getFeed()
     *  Description :  Fetching Data using Repository,
     */
    fun getFeed() {
        repository.getData()
    }
}