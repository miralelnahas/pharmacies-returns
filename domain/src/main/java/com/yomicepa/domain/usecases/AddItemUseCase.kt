package com.yomicepa.domain.usecases

import com.yomicepa.data.network.returnRequests.addItem.AddItemBody
import com.yomicepa.data.repositories.returnRequests.ReturnRequestsRepository
import com.yomicepa.domain.models.Item
import com.yomicepa.domain.usecases.Mapper.toItem
import javax.inject.Inject

class AddItemUseCase @Inject constructor(private val returnRequestsRepository: ReturnRequestsRepository) {
    suspend operator fun invoke(
        requestId: Int,
        ndc: String,
        description: String,
        manufacturer: String,
        fullQuantity: String,
        partialQuantity: String,
        expirationDate: String,
        lotNumber: String
    ) : Result<Item> = returnRequestsRepository.addItem(
        requestId,
        AddItemBody(
            ndc,
            description,
            manufacturer,
            fullQuantity,
            partialQuantity,
            expirationDate,
            lotNumber
        )
    ).map {
        it.toItem()
    }
}