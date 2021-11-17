package com.joesemper.movingcarcompose

import android.app.Application
import com.joesemper.movingcarcompose.di.appModule
import com.joesemper.movingcarcompose.di.mainActivityModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CarApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CarApp)
            modules(
                listOf(
                    appModule,
                    mainActivityModule
                )
            )
        }
    }
}