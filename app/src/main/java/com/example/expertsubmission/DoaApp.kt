package com.example.expertsubmission

import android.app.Application
import android.content.Context
import com.example.core.di.coreModule
import com.example.expertsubmission.di.presentationModule
import com.google.android.play.core.splitcompat.SplitCompat
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.logger.Level

class DoaApp : Application() {
    private val moduleList = arrayListOf(coreModule, presentationModule)

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(this)
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@DoaApp)
        }
        loadKoinModules(
            moduleList
        )
    }

    override fun onTerminate() {
        super.onTerminate()
        unloadKoinModules(moduleList)
    }
}