package com.yomicepa.data.repositories.returnRequests

import androidx.paging.PagingData
import com.yomicepa.data.models.PagingResponse
import com.yomicepa.data.network.returnRequests.CreateRequestResponse
import com.yomicepa.data.network.returnRequests.ReturnRequestContent
import com.yomicepa.data.network.returnRequests.addItem.AddItemBody
import com.yomicepa.data.network.returnRequests.addItem.AddItemResponse

interface ReturnRequestsDataSource {
    fun getReturnRequests(): PagingResponse<PagingData<ReturnRequestContent>>

    suspend fun createReturnRequest(
        serviceType: String,
        wholesaleId: Int
    ): Result<CreateRequestResponse>

    suspend fun addItem(requestId: Int, addItemBody: AddItemBody): Result<AddItemResponse>
}