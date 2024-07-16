package com.yomicepa.data.repositories.returnRequests

import androidx.paging.PagingData
import com.yomicepa.data.models.PagingResponse
import com.yomicepa.data.network.returnRequests.CreateRequestResponse
import com.yomicepa.data.network.returnRequests.ReturnRequestContent
import com.yomicepa.data.network.returnRequests.items.ItemBody
import com.yomicepa.data.network.returnRequests.items.ItemResponse
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
        itemBody: ItemBody
    ): Result<ItemResponse> = returnRequestsDataSource.addItem(requestId, itemBody)

    override suspend fun getItems(requestId: Int): Result<List<ItemResponse>> =
        returnRequestsDataSource.getItems(requestId)

    override suspend fun deleteItem(requestId: Int, itemId: Int): Result<Unit> =
        returnRequestsDataSource.deleteItem(requestId, itemId)

    override suspend fun editItem(
        requestId: Int,
        itemId: Int,
        itemBody: ItemBody
    ): Result<ItemResponse> =
        returnRequestsDataSource.editItem(requestId, itemId, itemBody)

}