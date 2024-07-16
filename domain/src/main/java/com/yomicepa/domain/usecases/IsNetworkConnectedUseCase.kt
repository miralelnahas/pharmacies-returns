package com.yomicepa.domain.usecases

import com.yomicepa.data.managers.ConnectionManager
import javax.inject.Inject

class IsNetworkConnectedUseCase @Inject constructor(private val connectionManager: ConnectionManager) {
    operator fun invoke() = connectionManager.isNetworkAvailable()
}