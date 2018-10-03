package com.rashikaranpuria.wanderee.ui.variantSelector

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.rashikaranpuria.wanderee.R
import com.rashikaranpuria.wanderee.WandereeApplication
import com.rashikaranpuria.wanderee.data.api.model.ExcludeListItem
import com.rashikaranpuria.wanderee.data.api.model.VariantGroupsItem
import com.rashikaranpuria.wanderee.data.api.model.VariationsItem
import com.rashikaranpuria.wanderee.di.Module.VariantSelectorModule
import com.rashikaranpuria.wanderee.ui.base.BaseActivity
import kotlinx.android.synthetic.main.variant_selector_activity.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

class VariantSelectorActivity : BaseActivity(), IVariantSelectorView {

    @Inject
    override
    lateinit var mProgressDialog: ProgressDialog

    @Inject
    lateinit var mVariantSelectorPresenter: IVariantSelectorPresenter<IVariantSelectorView>

    // Recyclerview adapter for main recycler view containing variant groups and variant choices
    lateinit var mVariantsAdapter: VariantsGroupAdapter

    // variant list
    var mVariantsList: List<VariantGroupsItem>? = null

    // exclusion mapping
    lateinit var mExclusionMapping: HashMap<ExcludeListItem, MutableList<ExcludeListItem>>

    // selected options set
    val selectionSet = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.variant_selector_activity)
        (application as WandereeApplication).appComponent.variantSelectorComponent(VariantSelectorModule(this)).inject(this)
        // init recycler view
        recycler_view.layoutManager = LinearLayoutManager(this)
        mVariantsAdapter = VariantsGroupAdapter(this)
        recycler_view.setAdapter(mVariantsAdapter)
        // attach activity to presenter
        mVariantSelectorPresenter.onAttach(this)
    }

    override fun setVariantsDataInAdapter(variantGroups: List<VariantGroupsItem>?, excludeMap: HashMap<ExcludeListItem, MutableList<ExcludeListItem>>) {
        mVariantsList = variantGroups
        mExclusionMapping = excludeMap
        resetAdapterData()
    }

    // method that updates adapter with new data
    override fun resetAdapterData() {
        if (mVariantsList != null) {
            mVariantsAdapter.variantGroups = deepClone(mVariantsList!!)

//            mVariantsAdapter.variantGroups.removeAll { true }
//            mVariantsAdapter.variantGroups.addAll(mVariantsList!!.map { it.copy() })
        }
    }

    // subscribes to event sent by adapter to recalculate current state of variant data
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUpdateDataMessage(event: UpdateDataMessage) {
        updateListData(event.groupId, event.childId)
    }

    // recalculates current state of variant data
    override fun updateListData(mGroupId: String, mChildId: String) {
        // if mVariantList is not empty or null
        if (mVariantsList != null || !mVariantsList!!.isEmpty()) {

            // get selected group and child
            val selectedGroup = mVariantsList?.find { it.groupId == mGroupId }
            val selectedChild = selectedGroup?.variations?.find { it.id == mChildId }

            // if selected child is conflicted or not in stock or null return
            if (selectedGroup == null || selectedChild == null || selectedChild.isConflictingSelection || selectedChild.inStock == 0) {
                return
            }

            // update local selection set with new selection
            if (!selectionSet.containsKey(mGroupId))
                selectionSet.put(mGroupId, mChildId)
            else {
                selectionSet[mGroupId] = mChildId
            }

            // remove previous selections in this group and choose current selection

            selectedGroup
                    .variations
                    .forEach {
                        variationsItem ->
                        variationsItem.isSelected = variationsItem.id == mChildId
                    }

            // removing all conflicting selections
            mVariantsList!!.forEach { it.variations.forEach { it2 -> it2.isConflictingSelection = false } }

            // for each selected option find and mark conflicts
            for ((groupK, variantV) in selectionSet) {
                // get selection object item
                val selectedObj = mVariantsList!!.find { it.groupId == groupK }
                val selectedObjItem = ExcludeListItem(selectedObj!!.groupId, selectedObj.variations.find { it.id == variantV }!!.id)

                // if map has exclusion for selection object
                if (mExclusionMapping.containsKey(selectedObjItem) && mExclusionMapping[selectedObjItem]!!.size > 0) {
                    for (conflictingItem in mExclusionMapping[selectedObjItem]!!) {
                        mVariantsList!!
                                .find { it.groupId == conflictingItem.groupId }
                                ?.variations
                                ?.forEach {
                                    // if item is conflict remove selection and set conflict variable
                                    if (it.id == conflictingItem.variationId) {
                                        it.isConflictingSelection = true
                                        it.isSelected = false
                                    } else {
                                        // if item is not conflict unset conflict variable
                                        it.isConflictingSelection = false
                                    }
                                }
                        }
                    }
                }
            }
        resetAdapterData()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    // creates a deep clone of list to avoid issues due to references
    fun deepClone(list: List<VariantGroupsItem>): List<VariantGroupsItem> {
        val out = mutableListOf<VariantGroupsItem>()
        list.forEach {
            val outVariations = mutableListOf<VariationsItem>()
            it.variations.forEach { it2 ->
                outVariations.add(VariationsItem(it2.jsonMemberDefault, it2.isVeg, it2.price, it2.name, it2.inStock, it2.id, it2.isSelected, it2.isConflictingSelection)) }
            out.add(VariantGroupsItem(it.groupId, outVariations, it.name))
        }
        return out
    }

    override fun onDestroy() {
        super.onDestroy()
        mVariantSelectorPresenter.onDetach()
        mProgressDialog.dismiss()
    }
}