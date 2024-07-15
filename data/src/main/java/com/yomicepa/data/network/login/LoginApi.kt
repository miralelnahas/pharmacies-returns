package com.yomicepa.data.network.login

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("auth")
    suspend fun login(@Body body: LoginBody): Response<LoginResponse>
}