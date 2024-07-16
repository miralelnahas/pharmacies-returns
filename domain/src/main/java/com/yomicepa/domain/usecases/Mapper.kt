package com.yomicepa.domain.usecases

import com.yomicepa.data.network.returnRequests.ReturnRequestContent
import com.yomicepa.domain.models.ReturnRequest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Mapper {

    fun ReturnRequestContent.toReturnRequest(): ReturnRequest {
        returnRequest.apply {
            return ReturnRequest(
                id.toString(),
                SimpleDateFormat("dd-MM-yyyy mm:ss", Locale.US).format(Date(createdAt)),
                numberOfItems.toString(),
                returnRequestStatus,
                serviceType, associatedWholesaler
            )
        }
    }
}