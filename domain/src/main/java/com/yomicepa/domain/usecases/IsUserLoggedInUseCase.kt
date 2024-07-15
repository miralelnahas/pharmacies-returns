package com.yomicepa.domain.usecases

import com.yomicepa.data.repositories.user.UserRepository
import javax.inject.Inject

class IsUserLoggedInUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke() = userRepository.getToken().isNotEmpty()
}