package com.rashikaranpuria.wanderee.data.api.model

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class Variants(

    @field:SerializedName("variant_groups")
    val variantGroups: List<VariantGroupsItem>? = null,

    @field:SerializedName("exclude_list")
    val excludeList: List<List<ExcludeListItem>>? = null
) {
    override fun hashCode(): Int {
        var result = variantGroups?.hashCode() ?: 0
        result = 31 * result + (excludeList?.hashCode() ?: 0)
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Variants

        if (variantGroups != other.variantGroups) return false
        if (excludeList != other.excludeList) return false

        return true
    }
}