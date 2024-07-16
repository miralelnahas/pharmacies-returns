package com.yomicepa.domain.usecases

import androidx.paging.PagingData
import androidx.paging.map
import com.yomicepa.data.repositories.returnRequests.ReturnRequestsRepository
import com.yomicepa.domain.models.ReturnRequest
import com.yomicepa.domain.usecases.Mapper.toReturnRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetReturnRequestsUseCase @Inject constructor(private val returnRequestsRepository: ReturnRequestsRepository) {
    operator fun invoke(): Flow<PagingData<ReturnRequest>> =
        returnRequestsRepository.getReturnRequests().data.map {
            it.map {
                it.toReturnRequest()
            }
        }
}