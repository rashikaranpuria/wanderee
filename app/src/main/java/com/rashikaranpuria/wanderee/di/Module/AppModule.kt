package com.rashikaranpuria.wanderee.di.Module

import android.app.Application
import com.rashikaranpuria.wanderee.data.api.ApiManager
import com.rashikaranpuria.wanderee.data.api.DataManager
import com.rashikaranpuria.wanderee.data.api.IApiManager
import com.rashikaranpuria.wanderee.data.api.IDataManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {
    // application module provides
    // data manager and api manager as a singleton instance for the whole application

    @Provides
    @Singleton
    fun providesDataManager(mDataManager: DataManager): IDataManager = mDataManager

    @Provides
    @Singleton
    fun providesApiManager(mApiManager: ApiManager): IApiManager = mApiManager
}