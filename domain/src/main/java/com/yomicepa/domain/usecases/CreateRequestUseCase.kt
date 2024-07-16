package com.yomicepa.domain.usecases

import com.yomicepa.data.repositories.returnRequests.ReturnRequestsRepository
import com.yomicepa.domain.models.ServiceType
import javax.inject.Inject

class CreateRequestUseCase @Inject constructor(private val returnRequestsRepository: ReturnRequestsRepository) {
    suspend operator fun invoke(serviceType: ServiceType, wholesaleId: Int) =
        returnRequestsRepository.createReturnRequest(serviceType.name, wholesaleId).map {
            it.id
        }
}