package com.rashikaranpuria.wanderee.data.api.model

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class Variants(

    @field:SerializedName("variant_groups")
    val variantGroups: List<VariantGroupsItem>? = null,

    @field:SerializedName("exclude_list")
    val excludeList: List<List<ExcludeListItem>>? = null
)