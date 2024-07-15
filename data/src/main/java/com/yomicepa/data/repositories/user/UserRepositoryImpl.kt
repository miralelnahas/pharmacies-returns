package com.yomicepa.data.repositories.user

import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userLocalDataSource: UserLocalDataSource) :
    UserRepository {
    override suspend fun setToken(token: String) {
        userLocalDataSource.setToken(token)
    }

    override suspend fun removeToken() {
        userLocalDataSource.removeToken()
    }

    override fun getToken(): String = userLocalDataSource.getToken()
}