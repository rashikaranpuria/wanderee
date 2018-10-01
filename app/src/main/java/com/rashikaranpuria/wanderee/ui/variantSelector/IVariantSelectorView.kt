package com.rashikaranpuria.wanderee.ui.variantSelector

import com.rashikaranpuria.wanderee.data.api.model.ExcludeListItem
import com.rashikaranpuria.wanderee.data.api.model.VariantGroupsItem
import com.rashikaranpuria.wanderee.ui.base.IBaseView

interface IVariantSelectorView: IBaseView {
    abstract fun setVariantsDataInAdapter(variantGroups: List<VariantGroupsItem?>?, excludeList: List<List<ExcludeListItem?>?>?)
}