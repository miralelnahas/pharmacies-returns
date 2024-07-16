package com.yomicepa.domain.usecases

import com.yomicepa.data.repositories.user.UserRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke() =
        Result.success(userRepository.removeToken())
}