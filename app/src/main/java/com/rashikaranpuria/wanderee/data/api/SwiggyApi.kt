package com.rashikaranpuria.wanderee.data.api

import com.rashikaranpuria.wanderee.data.api.model.VariantResponse
import com.rashikaranpuria.wanderee.data.api.model.Variants
import io.reactivex.Single
import retrofit2.http.GET

interface SwiggyApi {

    // request type: get
    // segment added to base url: 3b0u2
    // params none
    // returns Single<VariantResponse>
    @GET("3b0u2")
    fun getVariantsResponse(): Single<VariantResponse>

}