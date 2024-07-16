package com.yomicepa.data.models

import kotlinx.coroutines.flow.Flow

data class PagingResponse<T>(
    val data: Flow<T>,
)