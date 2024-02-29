package com.myapplication

import android.app.Application
import root.AppInitializer
import org.koin.android.ext.koin.androidContext

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        AppInitializer.initialize(isDebug = true) {
            androidContext(this@MainApplication)
        }
    }}