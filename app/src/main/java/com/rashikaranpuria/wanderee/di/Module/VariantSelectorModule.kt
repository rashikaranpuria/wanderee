package com.rashikaranpuria.wanderee.di.Module

import com.rashikaranpuria.wanderee.ui.variantSelector.IVariantSelectorPresenter
import com.rashikaranpuria.wanderee.ui.variantSelector.IVariantSelectorView
import com.rashikaranpuria.wanderee.ui.variantSelector.VariantSelectorPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class VariantSelectorModule {

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    fun providePresenter(variantSelectorPresenter: VariantSelectorPresenter<IVariantSelectorView>): IVariantSelectorPresenter<IVariantSelectorView> = variantSelectorPresenter
}