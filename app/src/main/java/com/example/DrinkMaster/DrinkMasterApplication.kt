package com.example.DrinkMaster


import android.app.Application
import android.content.Context

class DrinkMasterApplication :Application() {

    object Globals {
        var appContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        Globals.appContext = applicationContext
    }

}