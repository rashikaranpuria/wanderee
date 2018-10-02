package com.rashikaranpuria.wanderee.data.api

import com.rashikaranpuria.wanderee.data.api.model.VariantResponse
import io.reactivex.Single

interface IApiManager {

    // implement method to fetch variants response data from api
    fun fetchVariantsData(): Single<VariantResponse>
}