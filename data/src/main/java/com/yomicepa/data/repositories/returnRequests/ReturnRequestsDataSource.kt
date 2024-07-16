package com.yomicepa.data.repositories.returnRequests

import androidx.paging.PagingData
import com.yomicepa.data.models.PagingResponse
import com.yomicepa.data.network.returnRequests.CreateRequestResponse
import com.yomicepa.data.network.returnRequests.ReturnRequestContent
import com.yomicepa.data.network.returnRequests.ReturnRequestResponse
import com.yomicepa.data.network.returnRequests.ReturnRequestsResponse

interface ReturnRequestsDataSource {
    fun getReturnRequests() : PagingResponse<PagingData<ReturnRequestContent>>

    suspend fun createReturnRequest(serviceType: String, wholesaleId: Int) : Result<CreateRequestResponse>
}