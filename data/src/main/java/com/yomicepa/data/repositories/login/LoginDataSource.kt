package com.yomicepa.data.repositories.login

import com.yomicepa.data.network.login.LoginResponse

interface LoginDataSource {
    suspend fun login(username: String, password: String) : Result<LoginResponse>
}