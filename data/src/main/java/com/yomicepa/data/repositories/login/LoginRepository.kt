package com.yomicepa.data.repositories.login

interface LoginRepository {
    suspend fun login(username: String, password: String) : Result<Boolean>
}