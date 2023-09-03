package com.example.runlistandroidclient.data.network.datemang

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.runlistandroidclient.di.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class TokenManager(private val context: Context) {

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val USER_KEY = stringPreferencesKey("user_name")
        private  val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        private  val USER_PASSWORD_KEY = stringPreferencesKey("user_password")
    }

    fun getToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    suspend fun saveToken(token: String?) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token!!
        }
    }

    suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
    /* fun getToken(): Flow<usermanager?> {
        return context.dataStore.data.map { preferences ->
           usermanager(
               token = preferences[TOKEN_KEY]!!,
               name =preferences[USER_NAME_KEY]!!,
               email = preferences[USER_EMAIL_KEY]!!,
               password = preferences[USER_PASSWORD_KEY]!!
           )

        }
    }

    suspend fun saveToken(token: String?) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token!!
        }
    }
   suspend fun saveToken(userToken: usermanager?) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = userToken.token
            preferences[USER_NAME_KEY] =userToken.name
            preferences[USER_EMAIL_KEY] = userToken.email
            preferences[USER_PASSWORD_KEY] =userToken.password
        }
    }

    suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }*/
    fun getUserEmail(): Flow<String?>{
        return context.dataStore.data.map { preferences ->
            preferences[USER_EMAIL_KEY]
        }
    }
    fun getUserPassword(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[USER_PASSWORD_KEY]
        }
    }
    suspend fun saveUser(email:String?,password:String?) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = email!!
            preferences[USER_PASSWORD_KEY] =password!!
        }
    }

    suspend fun deleteUser() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_EMAIL_KEY)
            preferences.remove(USER_PASSWORD_KEY)
        }
    }
}


