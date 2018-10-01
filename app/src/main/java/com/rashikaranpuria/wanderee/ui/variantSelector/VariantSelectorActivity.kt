package com.rashikaranpuria.wanderee.ui.variantSelector

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.rashikaranpuria.wanderee.R
import com.rashikaranpuria.wanderee.WandereeApplication
import com.rashikaranpuria.wanderee.data.api.model.ExcludeListItem
import com.rashikaranpuria.wanderee.data.api.model.VariantGroupsItem
import com.rashikaranpuria.wanderee.di.Module.VariantSelectorModule
import com.rashikaranpuria.wanderee.ui.base.BaseActivity
import kotlinx.android.synthetic.main.variant_selector.*
import javax.inject.Inject

class VariantSelectorActivity: BaseActivity(), IVariantSelectorView {

    @Inject
    lateinit var mVariantSelectorPresenter: IVariantSelectorPresenter<IVariantSelectorView>

    lateinit var mVariantsAdapter: VariantsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.variant_selector)
        (application as WandereeApplication).appComponent.variantSelectorComponent(VariantSelectorModule()).inject(this)

        mVariantSelectorPresenter.onAttach(this)

    }

    override fun setVariantsDataInAdapter(variantGroups: List<VariantGroupsItem?>?, excludeList: List<List<ExcludeListItem?>?>?) {
        Log.d("variants data: ", variantGroups.toString())
        Log.d("exclude list data: ", excludeList.toString())
        mVariantsAdapter = VariantsAdapter(this, variantGroups as List<VariantGroupsItem>)
        recycler_view.adapter = mVariantsAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        mVariantsAdapter.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        mVariantsAdapter.onRestoreInstanceState(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mVariantSelectorPresenter.onDetach()
    }
}