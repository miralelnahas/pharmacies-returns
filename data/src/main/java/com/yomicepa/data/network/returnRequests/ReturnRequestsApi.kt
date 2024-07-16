package com.yomicepa.data.network.returnRequests

import com.yomicepa.data.network.returnRequests.items.ItemBody
import com.yomicepa.data.network.returnRequests.items.ItemResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ReturnRequestsApi {
    @GET("pharmacies/${pharmacyId}/returnrequests")
    suspend fun getReturnRequests(@Query("page") page: Int): Response<ReturnRequestsResponse>

    @POST("pharmacies/${pharmacyId}/returnrequests")
    suspend fun createReturnRequest(@Body body: ReturnRequestBody): Response<CreateRequestResponse>

    @POST("pharmacies/${pharmacyId}/returnrequests/{requestId}/items")
    suspend fun addItem(
        @Body body: ItemBody,
        @Path("requestId") requestId: Int
    ): Response<ItemResponse>

    @GET("pharmacies/${pharmacyId}/returnrequests/{requestId}/items")
    suspend fun getItems(@Path("requestId") requestId: Int): Response<List<ItemResponse>>

    @DELETE("pharmacies/${pharmacyId}/returnrequests/{requestId}/items/{itemId}")
    suspend fun deleteItem(
        @Path("requestId") requestId: Int,
        @Path("itemId") itemId: Int
    ): Response<Unit>

    @PUT("pharmacies/${pharmacyId}/returnrequests/{requestId}/items/{itemId}")
    suspend fun editItem(
        @Path("requestId") requestId: Int,
        @Path("itemId") itemId: Int,
        @Body body: ItemBody
    ): Response<ItemResponse>


    companion object {
        const val pharmacyId = 191
    }

}