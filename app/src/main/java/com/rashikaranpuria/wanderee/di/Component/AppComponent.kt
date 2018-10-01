package com.rashikaranpuria.wanderee.di.Component

import com.rashikaranpuria.wanderee.di.Module.AppModule
import com.rashikaranpuria.wanderee.di.Module.NetworkModule
import com.rashikaranpuria.wanderee.di.Module.VariantSelectorModule
import dagger.Component
import javax.inject.Singleton

// application component provides all sub components
@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun variantSelectorComponent(variantSelectorModule: VariantSelectorModule): VariantSelectorComponent
}