package com.areeb.data.di

import com.yomicepa.data.datasources.LoginDataSourceImpl
import com.yomicepa.data.datasources.ReturnRequestsDataSourceImpl
import com.yomicepa.data.datasources.UserLocalDataSourceImpl
import com.yomicepa.data.repositories.login.LoginDataSource
import com.yomicepa.data.repositories.login.LoginRepository
import com.yomicepa.data.repositories.login.LoginRepositoryImpl
import com.yomicepa.data.repositories.returnRequests.ReturnRequestsDataSource
import com.yomicepa.data.repositories.returnRequests.ReturnRequestsRepository
import com.yomicepa.data.repositories.returnRequests.ReturnRequestsRepositoryImpl
import com.yomicepa.data.repositories.user.UserLocalDataSource
import com.yomicepa.data.repositories.user.UserRepository
import com.yomicepa.data.repositories.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoreComponents {

    @Binds
    fun loginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    fun loginRemoteDs(loginDataSourceImpl: LoginDataSourceImpl): LoginDataSource

    @Binds
    fun userRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    fun userLocalDs(userLocalDataSourceImpl: UserLocalDataSourceImpl): UserLocalDataSource

    @Binds
    fun returnRequestsRepository(returnRequestsRepositoryImpl: ReturnRequestsRepositoryImpl): ReturnRequestsRepository

    @Binds
    fun returnRequestsDs(returnRequestsDataSourceImpl: ReturnRequestsDataSourceImpl): ReturnRequestsDataSource

}