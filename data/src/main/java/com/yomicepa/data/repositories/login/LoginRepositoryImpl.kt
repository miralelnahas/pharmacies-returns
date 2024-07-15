package com.yomicepa.data.repositories.login

import com.yomicepa.data.repositories.user.UserLocalDataSource
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource,
    private val userLocalDataSource: UserLocalDataSource
) :
    LoginRepository {
    override suspend fun login(username: String, password: String): Result<Boolean> {
        return loginDataSource.login(username, password).mapCatching {
            userLocalDataSource.setToken(it.token)
            true
        }.recover { false }
    }
}