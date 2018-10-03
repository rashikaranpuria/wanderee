package com.rashikaranpuria.wanderee.data.api.model

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class ExcludeListItem(

    @field:SerializedName("group_id")
    val groupId: String,

    @field:SerializedName("variation_id")
    val variationId: String
) {
    override fun hashCode(): Int {
        var result = groupId.hashCode()
        result = 31 * result + variationId.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ExcludeListItem

        if (groupId != other.groupId) return false
        if (variationId != other.variationId) return false

        return true
    }
}