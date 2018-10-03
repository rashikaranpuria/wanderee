package com.rashikaranpuria.wanderee.di.Module

import android.content.Context
import com.rashikaranpuria.wanderee.R
import com.rashikaranpuria.wanderee.ui.variantSelector.IVariantSelectorPresenter
import com.rashikaranpuria.wanderee.ui.variantSelector.IVariantSelectorView
import com.rashikaranpuria.wanderee.ui.variantSelector.VariantSelectorActivity
import com.rashikaranpuria.wanderee.ui.variantSelector.VariantSelectorPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.indeterminateProgressDialog

@Module
class VariantSelectorModule(private val context: Context) {

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    fun providePresenter(variantSelectorPresenter: VariantSelectorPresenter<IVariantSelectorView>): IVariantSelectorPresenter<IVariantSelectorView> = variantSelectorPresenter

    @Provides
    fun provideProgressDialog() =
            context.indeterminateProgressDialog(context.getString(R.string.please_wait)).apply {
                hide()
            }
}