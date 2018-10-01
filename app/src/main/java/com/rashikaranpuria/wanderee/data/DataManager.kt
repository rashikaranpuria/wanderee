package com.rashikaranpuria.wanderee.data.api

import javax.inject.Inject

class DataManager @Inject constructor(val mApiManager: ApiManager) : IDataManager {

    // fetches master data from api manager
//    override fun fetchMasterData() = mApiManager.fetchMasterData()

    // fetches product category data from api manager using data url
//    override fun fetchCategoryData(url: String): Single<List<ProductDataResponse>> = mApiManager.fetchCategoryFromUrl(url)
}