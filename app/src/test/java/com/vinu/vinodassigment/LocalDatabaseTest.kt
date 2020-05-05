package com.vinu.vinodassigment

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.google.gson.Gson
import com.vinu.vinodassigment.api.RetrofitBuilder
import com.vinu.vinodassigment.dao.ResponseDao
import com.vinu.vinodassigment.database.FeedRoomDatabase
import com.vinu.vinodassigment.models.ResponseDataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@Config(manifest= Config.NONE)
class LocalDatabaseTest {
    private lateinit var responseDao: ResponseDao
    private lateinit var db: FeedRoomDatabase

    @Before
    fun setup() {
        FeedRoomDatabase.TEST_MODE = true
        db = FeedRoomDatabase.getDatabase(ApplicationProvider.getApplicationContext())
        responseDao =
            FeedRoomDatabase.getDatabase(ApplicationProvider.getApplicationContext()).newsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeReadNewsFeed() {
        AndroidNetworking.get("https://itunes.apple.com/us/rss/newfreeapplications/limit=2/json")
            .setPriority(Priority.LOW)
            .build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    val responseData = Gson().fromJson(response, ResponseDataModel::class.java)
                    CoroutineScope(Dispatchers.Default).launch {
                        responseData?.let {
                            responseDao.insertResponse(responseData)
                        }
                    }
                }

                override fun onError(anError: ANError?) {
                }

            })
    }
}