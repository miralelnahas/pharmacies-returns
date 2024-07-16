package com.yomicepa.domain.usecases

import com.yomicepa.data.network.returnRequests.items.ItemBody
import com.yomicepa.data.repositories.returnRequests.ReturnRequestsRepository
import com.yomicepa.domain.models.Item
import com.yomicepa.domain.usecases.Mapper.toItem
import javax.inject.Inject

class EditItemUseCase @Inject constructor(private val returnRequestsRepository: ReturnRequestsRepository) {
    suspend operator fun invoke(
        requestId: Int,
        item: Item,
        newDescription: String
    ): Result<Item> = item.let {
        returnRequestsRepository.editItem(
            requestId,
            it.id,
            ItemBody(
                it.ndc,
                newDescription,
                it.manufacturer,
                it.fullQuantity,
                it.partialQuantity,
                it.expirationDate,
                it.lotNumber
            )
        ).map {
            it.toItem()
        }
    }
}