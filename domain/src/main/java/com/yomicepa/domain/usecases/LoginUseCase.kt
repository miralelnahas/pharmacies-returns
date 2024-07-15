package com.yomicepa.domain.usecases

import com.yomicepa.data.repositories.login.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(username: String, password: String) =
        loginRepository.login(username, password)
}