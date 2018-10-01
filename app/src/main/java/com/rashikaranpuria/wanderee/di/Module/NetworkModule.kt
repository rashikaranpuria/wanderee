package com.rashikaranpuria.wanderee.di.Module

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.rashikaranpuria.wanderee.data.api.WandereeApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    // Network module provides retrofit for api base url and mercari test api as retrofit api

    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder()
            .baseUrl("https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesWandereeApi(retrofit: Retrofit) = retrofit.create(WandereeApi::class.java)
}