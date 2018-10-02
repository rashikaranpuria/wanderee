package com.rashikaranpuria.wanderee.ui.variantSelector

import com.rashikaranpuria.wanderee.data.DataManager
import com.rashikaranpuria.wanderee.data.api.model.ExcludeListItem
import com.rashikaranpuria.wanderee.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class VariantSelectorPresenter<V: IVariantSelectorView> @Inject constructor(private val mDataManager: DataManager, private val mCompositeDisposable: CompositeDisposable): IVariantSelectorPresenter<V>, BasePresenter<V>() {

    // fetch all data from swiggy api
    override fun onAttach(v: V) {
        super.onAttach(v)
        mCompositeDisposable.add(
                mDataManager.fetchVariantsData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy (
                            onSuccess = {
                                // send variants list and eclusion mapping to adapter via view
                                view?.setVariantsDataInAdapter(it.variants?.variantGroups, calcExclusionMapping(it.variants?.excludeList))
                            },
                            onError = {
                                view?.error(it.localizedMessage?:"Unable to fetch data from server")
                            }
                        )
        )
    }

    // create a mapping of data such that for every (group id, child id)
    // we know which (group id, child id)s to ignore
    // structure: (group id, child id) -> [(group id1, child id1),(group id2, child id2),(group id3, child id3),...]
    fun calcExclusionMapping(exclusionList: List<List<ExcludeListItem>>?): HashMap<ExcludeListItem, MutableList<ExcludeListItem>> {
        val hm = HashMap<ExcludeListItem, MutableList<ExcludeListItem>>()
        if (exclusionList != null) {
            for (exclusionRuleList in exclusionList) {
                for (item in exclusionRuleList) {
                    if (!hm.containsKey(item))
                        hm[item] = mutableListOf()
                    hm[item]!!.addAll(exclusionRuleList.filter { it != item })
                }
            }

        }
        return hm
    }

    override fun onDetach() {
        super.onDetach()
        mCompositeDisposable.dispose()
    }
}