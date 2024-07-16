package com.yomicepa.data.datasources

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yomicepa.data.models.PagingResponse
import com.yomicepa.data.network.RetrofitClientExt.apiCall
import com.yomicepa.data.network.base.BasePagingSource
import com.yomicepa.data.network.returnRequests.CreateRequestResponse
import com.yomicepa.data.network.returnRequests.ReturnRequestBody
import com.yomicepa.data.network.returnRequests.ReturnRequestContent
import com.yomicepa.data.network.returnRequests.ReturnRequestsApi
import com.yomicepa.data.network.returnRequests.ReturnRequestsPagingSource
import com.yomicepa.data.network.returnRequests.addItem.AddItemBody
import com.yomicepa.data.network.returnRequests.addItem.AddItemResponse
import com.yomicepa.data.repositories.returnRequests.ReturnRequestsDataSource
import retrofit2.Retrofit
import javax.inject.Inject

class ReturnRequestsDataSourceImpl @Inject constructor(retrofit: Retrofit) :
    ReturnRequestsDataSource {
    val returnRequestsApi = retrofit.create(ReturnRequestsApi::class.java)
    override fun getReturnRequests(): PagingResponse<PagingData<ReturnRequestContent>> =
        PagingResponse(
            Pager(
                PagingConfig(
                    pageSize = BasePagingSource.LIST_PAGE_SIZE,
                    enablePlaceholders = false,
                    initialLoadSize = BasePagingSource.LIST_PAGE_SIZE
                )
            ) {
                ReturnRequestsPagingSource(returnRequestsApi)
            }.flow
        )

    override suspend fun createReturnRequest(
        serviceType: String, wholesaleId: Int
    ): Result<CreateRequestResponse> =
        apiCall {
            returnRequestsApi.createReturnRequest(ReturnRequestBody(serviceType, wholesaleId))
        }

    override suspend fun addItem(
        requestId: Int,
        addItemBody: AddItemBody
    ): Result<AddItemResponse> =
        apiCall {
            returnRequestsApi.addItem(addItemBody, requestId)
        }
}