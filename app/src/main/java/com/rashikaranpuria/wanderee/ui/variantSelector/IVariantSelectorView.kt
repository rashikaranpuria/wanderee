package com.rashikaranpuria.wanderee.ui.variantSelector

import com.rashikaranpuria.wanderee.data.api.model.ExcludeListItem
import com.rashikaranpuria.wanderee.data.api.model.VariantGroupsItem
import com.rashikaranpuria.wanderee.ui.base.IBaseView

interface IVariantSelectorView: IBaseView {
    fun setVariantsDataInAdapter(variantGroups: List<VariantGroupsItem>?, excludeMap: HashMap<ExcludeListItem, MutableList<ExcludeListItem>>)
    fun resetAdapterData()
    fun updateListData(groupPosition: Int, childPosition: Int)
}