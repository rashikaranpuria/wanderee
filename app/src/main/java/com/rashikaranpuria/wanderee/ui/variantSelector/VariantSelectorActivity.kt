package com.rashikaranpuria.wanderee.ui.variantSelector

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.variant_selector_activity)
        (application as WandereeApplication).appComponent.variantSelectorComponent(VariantSelectorModule()).inject(this)
        mVariantsAdapter = VariantsAdapter(this, listOf(), mapOf())
        list_view.setAdapter(mVariantsAdapter)
        list_view.setOnChildClickListener{ parent, v, groupPosition, childPosition, id ->

            Log.d("ALERTC", "SEE THIS")
            false
//            override fun onChildClick(parent: ExpandableListView, v: View,
//                    int groupPosition, int childPosition, long id): Boolean {
//                Toast.makeText(
//                        getApplicationContext(),
//                        expandableListTitle.get(groupPosition)
//                                + " -> "
//                                + expandableListDetail.get(
//                                expandableListTitle.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT
//                ).show();
//                return false;
//            }
//        });
    }
        mVariantSelectorPresenter.onAttach(this)

    }

    override fun setVariantsDataInAdapter(variantGroups: List<VariantGroupsItem?>?, excludeList: List<List<ExcludeListItem?>?>?) {
        Log.d("variants data: ", variantGroups.toString())
        Log.d("exclude list data: ", excludeList.toString())
        mVariantsAdapter.variantGroups = variantGroups as List<VariantGroupsItem>
        mVariantsAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        mVariantSelectorPresenter.onDetach()
    }
}