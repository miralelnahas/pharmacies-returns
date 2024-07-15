package com.yomicepa.data.network

import com.yomicepa.data.repositories.user.UserRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(private val userRepository: UserRepository) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request().newBuilder()
        val token = userRepository.getToken()
        request = request.addHeader("Authorization", "Bearer $token")
        return chain.proceed(request.build())
    }
}