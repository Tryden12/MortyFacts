package com.tryden.mortyfacts

import android.app.Application
import android.content.Context

class MortyFactsApplication: Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}