package com.yomicepa.data.repositories.user

interface UserLocalDataSource {
    suspend fun setToken(token: String)
    suspend fun removeToken()
    fun getToken(): String
}