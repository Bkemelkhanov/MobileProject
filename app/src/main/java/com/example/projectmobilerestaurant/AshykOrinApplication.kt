package com.example.projectmobilerestaurant

import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import com.example.projectmobilerestaurant.di.AppComponent
import com.example.projectmobilerestaurant.di.DaggerAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

class AshykOrinApplication : MultiDexApplication() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        timberInit()
        AndroidThreeTen.init(this)
    }

    private fun timberInit() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}