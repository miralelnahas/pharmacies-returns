package com.yomicepa.data.network.returnRequests

data class ReturnRequestResponse(
    val id: Int,
    val createdAt: Long,
    val returnRequestStatus: String,
    val serviceType: String,
    val associatedWholesaler: String = ""
)