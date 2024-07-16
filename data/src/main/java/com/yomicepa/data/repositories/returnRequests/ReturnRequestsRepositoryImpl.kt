package com.yomicepa.data.repositories.returnRequests

import androidx.paging.PagingData
import com.yomicepa.data.models.PagingResponse
import com.yomicepa.data.network.returnRequests.ReturnRequestContent
import com.yomicepa.data.network.returnRequests.ReturnRequestsResponse
import javax.inject.Inject

class ReturnRequestsRepositoryImpl @Inject constructor(private val returnRequestsDataSource: ReturnRequestsDataSource) :
    ReturnRequestsRepository {
    override fun getReturnRequests(): PagingResponse<PagingData<ReturnRequestContent>> = returnRequestsDataSource.getReturnRequests()

}