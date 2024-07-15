package com.yomicepa.data.repositories.user

interface UserRepository {
    suspend fun setToken(token: String)
    suspend fun removeToken()
    fun getToken(): String
}