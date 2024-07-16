package com.yomicepa.data.network.returnRequests.addItem

data class AddItemBody(
    val ndc: String,
    val description:String,
    val manufacturer: String,
    val fullQuantity: String,
    val partialQuantity: String,
    val expirationDate: String,
    val lotNumber: String,
    val packageSize: String = "200",
    val requestType : String = "csc",
    val name: String = "Item Name",
    val strength: String = "Strong",
    val dosage: String = "alssot",
    val status: String = "PENDING"
)