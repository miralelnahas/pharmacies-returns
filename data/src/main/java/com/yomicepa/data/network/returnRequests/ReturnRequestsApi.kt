package com.yomicepa.data.network.returnRequests

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ReturnRequestsApi {
    @GET("pharmacies/${pharmacyId}/returnrequests")
    suspend fun getReturnRequests(@Query("page") page: Int): Response<ReturnRequestsResponse>

    @POST("pharmacies/${pharmacyId}/returnrequests")
    suspend fun createReturnRequest(@Body body: ReturnRequestBody) : Response<CreateRequestResponse>

    companion object {
        const val pharmacyId = 191
    }

}