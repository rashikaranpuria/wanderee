package com.rashikaranpuria.wanderee.data.api

interface WandereeApi {

    // request type: get
    // segment added to base url:  master.json
    // params none
    // returns Single<List<MasterResponse>>
//    @GET("master.json")
//    fun getMasterResponse(): Single<List<MasterResponse>>

    // request type: get
    // params: url
    // returns Single<List<ProductDataResponse>>
//    @GET
//    fun getCategoryData(@Url url: String): Single<List<ProductDataResponse>>
}