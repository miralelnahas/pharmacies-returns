package com.yomicepa.data.network.returnRequests.items

class ItemResponse(
    val id: Int,
    val ndc: String,
    val description:String,
    val manufacturer: String,
    val fullQuantity: String,
    val partialQuantity: String,
    val expirationDate: String,
    val lotNumber: String
)