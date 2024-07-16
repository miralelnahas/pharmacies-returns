package com.yomicepa.data.network.returnRequests.addItem

class AddItemResponse(
    val id: Int,
    val ndc: String,
    val description:String,
    val manufacturer: String,
    val fullQuantity: String,
    val partialQuantity: String,
    val expirationDate: String,
    val lotNumber: String
)