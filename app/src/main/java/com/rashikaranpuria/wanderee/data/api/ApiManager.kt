package com.rashikaranpuria.wanderee.data.api

import io.reactivex.Single
import javax.inject.Inject

class ApiManager @Inject constructor(val wandereeApi: WandereeApi) : IApiManager {

    // fetches master data from mercari test api
//    override fun fetchMasterData() = wandereeApi.getMasterResponse()

    // fetches product category data from mercari test api using data url
//    override fun fetchCategoryFromUrl(url: String): Single<List<ProductDataResponse>> = wandereeApi.getCategoryData(url)
}