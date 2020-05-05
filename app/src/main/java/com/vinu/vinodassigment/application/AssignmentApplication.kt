package com.vinu.vinodassigment.application

import android.app.Application
import com.androidnetworking.AndroidNetworking

class AssignmentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext);
    }
}