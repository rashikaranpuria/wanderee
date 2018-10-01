package com.rashikaranpuria.wanderee.data

import com.rashikaranpuria.wanderee.data.api.ApiManager
import javax.inject.Inject

class DataManager @Inject constructor(val mApiManager: ApiManager) : IDataManager {

    // fetches variants response data from api manager
    override fun fetchVariantsData() = mApiManager.fetchVariantsData()
}