package com.yomicepa.domain.usecases

import com.yomicepa.data.network.returnRequests.ReturnRequestContent
import com.yomicepa.data.network.returnRequests.ReturnRequestResponse
import com.yomicepa.data.network.returnRequests.addItem.AddItemResponse
import com.yomicepa.domain.models.Item
import com.yomicepa.domain.models.ReturnRequest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Mapper {

    fun ReturnRequestContent.toReturnRequest(): ReturnRequest {
        returnRequest.apply {
            return toReturnRequest(numberOfItems)
        }
    }

    fun ReturnRequestResponse.toReturnRequest(numberOfItems: Int = 0): ReturnRequest =
        ReturnRequest(
            id.toString(),
            SimpleDateFormat("dd-MM-yyyy mm:ss", Locale.US).format(Date(createdAt)),
            numberOfItems.toString(),
            returnRequestStatus,
            serviceType, associatedWholesaler
        )

    fun AddItemResponse.toItem(): Item =
        Item(
            id,
            ndc,
            description,
            manufacturer,
            fullQuantity,
            partialQuantity,
            expirationDate,
            lotNumber
        )
}