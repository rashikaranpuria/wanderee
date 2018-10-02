package com.rashikaranpuria.wanderee.data.api.model

import android.os.Parcel
import android.os.Parcelable
import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class VariationsItem(

	@field:SerializedName("default")
	var jsonMemberDefault: Int = 1,

	@field:SerializedName("isVeg")
	val isVeg: Int = 1,

	@field:SerializedName("price")
	val price: Int = 0,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("inStock")
	val inStock: Int = 0,

	@field:SerializedName("id")
	val id: String,

	// variable to store selection of variant
	var isSelected: Boolean = jsonMemberDefault == 1,
	// variable to store if variant s conflicting another selection
	var isConflictingSelection: Boolean = false
)