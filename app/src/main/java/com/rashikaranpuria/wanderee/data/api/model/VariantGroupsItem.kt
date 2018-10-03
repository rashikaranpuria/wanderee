package com.rashikaranpuria.wanderee.data.api.model

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class VariantGroupsItem(

    @field:SerializedName("group_id")
    val groupId: String,

    @field:SerializedName("variations")
    val variations: List<VariationsItem>,

    @field:SerializedName("name")
    val name: String
) {
    override fun hashCode(): Int {
        var result = groupId.hashCode()
        result = 31 * result + variations.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as VariantGroupsItem

        if (groupId != other.groupId) return false
        if (variations != other.variations) return false
        if (name != other.name) return false

        return true
    }
}