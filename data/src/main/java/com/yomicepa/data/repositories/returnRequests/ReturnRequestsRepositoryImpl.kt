package com.yomicepa.data.repositories.returnRequests

import androidx.paging.PagingData
import com.yomicepa.data.models.PagingResponse
import com.yomicepa.data.network.returnRequests.CreateRequestResponse
import com.yomicepa.data.network.returnRequests.ReturnRequestContent
import com.yomicepa.data.network.returnRequests.ReturnRequestResponse
import com.yomicepa.data.network.returnRequests.addItem.AddItemBody
import com.yomicepa.data.network.returnRequests.addItem.AddItemResponse
import javax.inject.Inject

class ReturnRequestsRepositoryImpl @Inject constructor(private val returnRequestsDataSource: ReturnRequestsDataSource) :
    ReturnRequestsRepository {
    override fun getReturnRequests(): PagingResponse<PagingData<ReturnRequestContent>> =
        returnRequestsDataSource.getReturnRequests()

    override suspend fun createReturnRequest(
        serviceType: String,
        wholesaleId: Int
    ): Result<CreateRequestResponse> =
        returnRequestsDataSource.createReturnRequest(serviceType, wholesaleId)

    override suspend fun addItem(
        requestId: Int,
        addItemBody: AddItemBody
    ): Result<AddItemResponse> = returnRequestsDataSource.addItem(requestId, addItemBody)

}