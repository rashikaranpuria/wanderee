package com.rashikaranpuria.wanderee.data.api.model

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class VariantResponse(

    @field:SerializedName("variants")
    val variants: Variants? = null
) {
    override fun hashCode(): Int {
        return variants?.hashCode() ?: 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as VariantResponse

        if (variants != other.variants) return false

        return true
    }
}