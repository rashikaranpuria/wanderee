package com.rashikaranpuria.wanderee

import android.app.Application
import com.rashikaranpuria.wanderee.di.Component.AppComponent
import com.rashikaranpuria.wanderee.di.Component.DaggerAppComponent
import com.rashikaranpuria.wanderee.di.Module.AppModule

open class WandereeApplication : Application() {
    lateinit var appComponent: AppComponent

    // build dagger application component
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}