package com.rashikaranpuria.wanderee.di.Component

import com.rashikaranpuria.wanderee.di.Module.VariantSelectorModule
import com.rashikaranpuria.wanderee.ui.variantSelector.VariantSelectorActivity
import dagger.Subcomponent

@Subcomponent( modules = [VariantSelectorModule::class] )
interface VariantSelectorComponent {
    fun inject(variantSelectorActivity: VariantSelectorActivity)
}