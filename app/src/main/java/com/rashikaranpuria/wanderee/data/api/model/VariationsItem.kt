package com.rashikaranpuria.wanderee.data.api.model

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class VariationsItem(

	@field:SerializedName("default")
	val jsonMemberDefault: Int,

	@field:SerializedName("isVeg")
	val isVeg: Int,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("inStock")
	val inStock: Int,

	@field:SerializedName("id")
	val id: String
)