package com.rashikaranpuria.wanderee.ui.variantSelector

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.rashikaranpuria.wanderee.R
import com.rashikaranpuria.wanderee.WandereeApplication
import com.rashikaranpuria.wanderee.data.api.model.ExcludeListItem
import com.rashikaranpuria.wanderee.data.api.model.VariantGroupsItem
import com.rashikaranpuria.wanderee.di.Module.VariantSelectorModule
import com.rashikaranpuria.wanderee.ui.base.BaseActivity
import kotlinx.android.synthetic.main.variant_selector_activity.*
import javax.inject.Inject

class VariantSelectorActivity: BaseActivity(), IVariantSelectorView {

    @Inject
    lateinit var mVariantSelectorPresenter: IVariantSelectorPresenter<IVariantSelectorView>

    lateinit var mVariantsAdapter: VariantsAdapter
    var mVariantsList: List<VariantGroupsItem>? = null
    lateinit var mExclusionMapping: HashMap<ExcludeListItem, MutableList<ExcludeListItem>>
    val selectionSet = HashMap<Int, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.variant_selector_activity)
        (application as WandereeApplication).appComponent.variantSelectorComponent(VariantSelectorModule()).inject(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        mVariantsAdapter = VariantsAdapter(this)
        recycler_view.setAdapter(mVariantsAdapter)
        mVariantSelectorPresenter.onAttach(this)
    }

    override fun setVariantsDataInAdapter(variantGroups: List<VariantGroupsItem>?, excludeMap: HashMap<ExcludeListItem, MutableList<ExcludeListItem>>) {
        mVariantsList = variantGroups
        mExclusionMapping = excludeMap
        resetAdapterData()
    }

    override fun resetAdapterData() {
        if (mVariantsList != null) {
            mVariantsAdapter.variantGroups = mVariantsList as List<VariantGroupsItem>
            mVariantsAdapter.notifyDataSetChanged()
        }
    }

    override fun updateListData(groupPosition: Int, childPosition: Int) {
        // if mVariantList is not empty or null
        if (mVariantsList != null || mVariantsList!!.size <= 0) {
            var lastSelection = -1
            // update local selection set
            if (!selectionSet.containsKey(groupPosition))
                selectionSet.put(groupPosition, childPosition)
            else {
                lastSelection = selectionSet[groupPosition]!!
                selectionSet[groupPosition] = childPosition
            }

            // remove previous selections in this group and choose current selection
            mVariantsList!![groupPosition].variations.forEachIndexed { index, variationsItem ->
                variationsItem.isSelected = index == childPosition
            }

            // removing all conflicting selections
            mVariantsList!!.forEach { it.variations.forEach { it2 -> it2.isConflictingSelection = false } }
                for ((groupK, variantV) in selectionSet) {
                // get selection item
                val selectedGroup = mVariantsList!![groupK]
                val selectedItem = ExcludeListItem(selectedGroup.groupId, selectedGroup.variations[variantV].id)

                if (mExclusionMapping.containsKey(selectedItem) && mExclusionMapping[selectedItem]!!.size > 0) {
                }
                }
        }

        // compute conflicts resulting from change in selection
    }

    /*
    *
    // get selected item
    val selectedGroup = mVariantsList!![groupPosition]
    val selectedItem = ExcludeListItem(selectedGroup.groupId, selectedGroup.variations[childPosition].id)

    // set conflicting items isConflictingSelection variable
    if (mExclusionMapping.containsKey(selectedItem) && mExclusionMapping[selectedItem]!!.size > 0) {
        for (conflictingItem in mExclusionMapping[selectedItem]!!) {
            mVariantsList!!
                .find { it.groupId == conflictingItem.groupId }
                ?.variations
                ?.forEach {
                    // if item is conflict remove selection and set conflict variable
                    if (it.id == conflictingItem.variationId) {
                        it.isConflictingSelection = true
                        it.isSelected = false
                    }
                    else {
                        // if item is not conflict unset conflict variable
                        it.isConflictingSelection = false
                    }
                }
        }

                    */

    override fun onDestroy() {
        super.onDestroy()
        mVariantSelectorPresenter.onDetach()
    }
}