package com.rashikaranpuria.wanderee.data

import com.rashikaranpuria.wanderee.data.api.model.VariantResponse
import io.reactivex.Single

interface IDataManager {
//     implement this method to fetch variants response data
    fun fetchVariantsData(): Single<VariantResponse>

}