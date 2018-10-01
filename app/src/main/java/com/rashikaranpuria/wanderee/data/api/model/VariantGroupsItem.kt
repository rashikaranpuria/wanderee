package com.rashikaranpuria.wanderee.data.api.model

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

@Generated("com.robohorse.robopojogenerator")
 data class VariantGroupsItem(

	@field:SerializedName("group_id")
	val groupId: String,

	@field:SerializedName("variations")
	val variations: List<VariationsItem>,

	@field:SerializedName("name")
	val name: String
)
{
    fun toExpandableGroup(): ExpandableGroup<VariationsItem> {
        return ExpandableGroup(name, variations)
    }
}