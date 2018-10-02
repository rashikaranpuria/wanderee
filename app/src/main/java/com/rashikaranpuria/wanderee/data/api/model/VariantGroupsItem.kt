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
)