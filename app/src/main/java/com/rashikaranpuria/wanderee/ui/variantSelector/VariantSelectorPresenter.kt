package com.rashikaranpuria.wanderee.ui.variantSelector

import com.rashikaranpuria.wanderee.data.DataManager
import com.rashikaranpuria.wanderee.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class VariantSelectorPresenter<V: IVariantSelectorView> @Inject constructor(private val mDataManager: DataManager, private val mCompositeDisposable: CompositeDisposable): IVariantSelectorPresenter<V>, BasePresenter<V>() {

    override fun onAttach(v: V) {
        super.onAttach(v)
        mCompositeDisposable.add(
                mDataManager.fetchVariantsData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy (
                            onSuccess = {
                                view?.setVariantsDataInAdapter(it.variants?.variantGroups, it.variants?.excludeList)
                            },
                            onError = {
                                view?.error(it.localizedMessage?:"Unable to fetch data from server")
                            }
                        )
        )
    }

    override fun onDetach() {
        super.onDetach()
        mCompositeDisposable.dispose()
    }
}