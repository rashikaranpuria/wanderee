package com.rashikaranpuria.wanderee.data.api.model

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class VariantGroupsItem(

	@field:SerializedName("group_id")
	val groupId: String,

	@field:SerializedName("variations")
	val variations: List<VariationsItem>? = null,

	@field:SerializedName("name")
	val name: String
)