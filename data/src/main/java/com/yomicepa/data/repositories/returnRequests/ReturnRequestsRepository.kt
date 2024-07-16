package com.yomicepa.data.repositories.returnRequests

import androidx.paging.PagingData
import com.yomicepa.data.models.PagingResponse
import com.yomicepa.data.network.returnRequests.CreateRequestResponse
import com.yomicepa.data.network.returnRequests.ReturnRequestContent
import com.yomicepa.data.network.returnRequests.items.ItemBody
import com.yomicepa.data.network.returnRequests.items.ItemResponse

interface ReturnRequestsRepository {
    fun getReturnRequests() : PagingResponse<PagingData<ReturnRequestContent>>

    suspend fun createReturnRequest(serviceType: String, wholesaleId: Int) : Result<CreateRequestResponse>

    suspend fun addItem(requestId: Int, itemBody: ItemBody) : Result<ItemResponse>

    suspend fun getItems(requestId: Int) : Result<List<ItemResponse>>

    suspend fun deleteItem(requestId: Int, itemId: Int): Result<Unit>
    suspend fun editItem(requestId: Int, itemId: Int, itemBody: ItemBody) : Result<ItemResponse>

}