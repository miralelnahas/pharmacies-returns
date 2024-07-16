package com.yomicepa.data.network.returnRequests

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ReturnRequestsApi {
    @GET("pharmacies/191/returnrequests")
    suspend fun getReturnRequests(@Query("page") page: Int): Response<ReturnRequestsResponse>
}