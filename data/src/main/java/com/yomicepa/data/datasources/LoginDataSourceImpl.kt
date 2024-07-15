package com.yomicepa.data.datasources

import com.yomicepa.data.network.RetrofitClientExt.apiCall
import com.yomicepa.data.network.login.LoginApi
import com.yomicepa.data.network.login.LoginBody
import com.yomicepa.data.network.login.LoginResponse
import com.yomicepa.data.repositories.login.LoginDataSource
import retrofit2.Retrofit
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(retrofit: Retrofit) : LoginDataSource {
    private val loginApi = retrofit.create(LoginApi::class.java)

    override suspend fun login(username: String, password: String): Result<LoginResponse> {
        return apiCall {
            loginApi.login(LoginBody(username, password))
        }
    }
}