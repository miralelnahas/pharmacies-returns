package com.yomicepa.data.network.returnRequests

import com.yomicepa.data.network.returnRequests.addItem.AddItemBody
import com.yomicepa.data.network.returnRequests.addItem.AddItemResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ReturnRequestsApi {
    @GET("pharmacies/${pharmacyId}/returnrequests")
    suspend fun getReturnRequests(@Query("page") page: Int): Response<ReturnRequestsResponse>

    @POST("pharmacies/${pharmacyId}/returnrequests")
    suspend fun createReturnRequest(@Body body: ReturnRequestBody): Response<CreateRequestResponse>

    @POST("pharmacies/${pharmacyId}/returnrequests/{requestId}/items")
    suspend fun addItem(
        @Body body: AddItemBody,
        @Path("requestId") requestId: Int
    ): Response<AddItemResponse>


    companion object {
        const val pharmacyId = 191
    }

}