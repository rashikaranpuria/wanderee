package com.rashikaranpuria.wanderee.data.api

import com.rashikaranpuria.wanderee.data.api.model.VariantResponse
import io.reactivex.Single
import javax.inject.Inject

class ApiManager @Inject constructor(private val swiggyApi: SwiggyApi) : IApiManager {

    override fun fetchVariantsData(): Single<VariantResponse> = swiggyApi.getVariantsResponse()
}