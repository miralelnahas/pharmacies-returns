package com.yomicepa.data.datasources

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.yomicepa.data.repositories.user.UserLocalDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

val Context.UserDataStore by preferencesDataStore("user")

class UserLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) : UserLocalDataSource {
    private val userDataStore = context.UserDataStore
    override suspend fun setToken(token: String) {
        val key = stringPreferencesKey(TOKEN)
        userDataStore.edit { pref ->
            pref[key] = gson.toJson(token)
        }
    }

    override suspend fun removeToken() {
        val key = stringPreferencesKey(TOKEN)
        userDataStore.edit { pref ->
            pref[key] = gson.toJson(null)
        }
    }

    override fun getToken(): String {
        val key = stringPreferencesKey(TOKEN)
        return runBlocking {
            val preferences = userDataStore.data.first()
            preferences[key]?.let {
                gson.fromJson(it, String::class.java)
            } ?: ""
        }
    }

    companion object {
        private const val TOKEN = "token"
    }
}