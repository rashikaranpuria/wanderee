package com.rashikaranpuria.wanderee.data.api.model

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class VariationsItem(
    @field:SerializedName("default")
    val jsonMemberDefault: Int = 1,

    // default assumption that item is veg
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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as VariationsItem

        if (jsonMemberDefault != other.jsonMemberDefault) return false
        if (isVeg != other.isVeg) return false
        if (price != other.price) return false
        if (name != other.name) return false
        if (inStock != other.inStock) return false
        if (id != other.id) return false
        if (isSelected != other.isSelected) return false
        if (isConflictingSelection != other.isConflictingSelection) return false

        return true
    }

    override fun hashCode(): Int {
        var result = jsonMemberDefault
        result = 31 * result + isVeg
        result = 31 * result + price
        result = 31 * result + name.hashCode()
        result = 31 * result + inStock
        result = 31 * result + id.hashCode()
        result = 31 * result + isSelected.hashCode()
        result = 31 * result + isConflictingSelection.hashCode()
        return result
    }
}