package com.vinu.vinodassigment

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.google.gson.Gson
import com.vinu.vinodassigment.models.ResponseDataModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.annotation.Config


@RunWith(JUnit4::class)
@Config(manifest = Config.NONE)
class ApiTest {

    @Test
    fun testListIsNull() {
        AndroidNetworking.get("https://itunes.apple.com/us/rss/newfreeapplications/limit=2/json")
            .setPriority(Priority.LOW)
            .build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    val responseData = Gson().fromJson(response, ResponseDataModel::class.java)
                    assert(responseData.feed?.entry != null)
                }

                override fun onError(anError: ANError?) {

                }

            })
    }
}