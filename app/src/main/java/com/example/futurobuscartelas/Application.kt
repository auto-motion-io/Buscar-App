package com.example.futurobuscartelas

import android.app.Application
import com.example.futurobuscartelas.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class Application:Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@Application)
            modules(appModule)
        }
    }
}