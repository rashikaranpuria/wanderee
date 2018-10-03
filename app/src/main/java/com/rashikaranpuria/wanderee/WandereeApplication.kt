package com.rashikaranpuria.wanderee

import android.app.Application
import com.rashikaranpuria.wanderee.di.Component.AppComponent
import com.rashikaranpuria.wanderee.di.Component.DaggerAppComponent
import com.rashikaranpuria.wanderee.di.Module.AppModule
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

open class WandereeApplication : Application() {
    lateinit var appComponent: AppComponent

    // build dagger application component
    override fun onCreate() {
        super.onCreate()

        // init Leak Canary Analyzer process
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)

        // Build Dagger component
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        // Timber log tree
        Timber.plant(Timber.DebugTree())
    }
}