package com.yomicepa.domain.usecases

import com.yomicepa.data.repositories.returnRequests.ReturnRequestsRepository
import com.yomicepa.domain.models.Item
import com.yomicepa.domain.usecases.Mapper.toItem
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(private val returnRequestsRepository: ReturnRequestsRepository) {
    suspend operator fun invoke(requestId: Int) : Result<List<Item>> = returnRequestsRepository.getItems(requestId).map {
        it.map {
            it.toItem()
        }
    }
}