package com.yomicepa.domain.usecases

import com.yomicepa.data.repositories.returnRequests.ReturnRequestsRepository
import javax.inject.Inject

class DeleteItemUseCase @Inject constructor(private val returnRequestsRepository: ReturnRequestsRepository) {
    suspend operator fun invoke(requestId: Int, itemId: Int) =
        returnRequestsRepository.deleteItem(requestId, itemId)
}