package com.yomicepa.domain.models

data class Item(
    val id: Int,
    val ndc: String,
    val description: String,
    val manufacturer: String,
    val fullQuantity: String?="",
    val partialQuantity: String,
    val expirationDate: String,
    val lotNumber: String
)