package com.yomicepa.domain.models

data class ReturnRequest(
    val id: String,
    val createdAt: String,
    val itemsCount: String,
    val status: String,
    val serviceType: String,
    //TODO: handle wholesaler
    val associatedWholesaler: String?
)