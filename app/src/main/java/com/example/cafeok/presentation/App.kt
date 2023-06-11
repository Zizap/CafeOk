package com.example.cafeok.presentation

import android.app.Application
import com.example.cafeok.presentation.di.basket
import com.example.cafeok.presentation.di.coffee
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(coffee, basket)
        }
    }
}