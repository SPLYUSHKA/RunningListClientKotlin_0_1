package com.example.runlistandroidclient.helpers

import com.example.runlistandroidclient.data.network.ApiUserServises
import com.example.runlistandroidclient.data.network.datemang.TokenManager
import com.example.runlistandroidclient.di.AppModule
import com.example.runlistandroidclient.model.LoginResponse
import com.example.runlistandroidclient.model.ModelDTO.UserDTO
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager
): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {

            tokenManager.getToken().first()
        }
        val user = runBlocking {
            "  "
            tokenManager.getUserEmail().first()
            tokenManager.getUserPassword().first()
            as UserDTO
        }
        return runBlocking {
            val newToken = getNewToken(token,user)
            if (!newToken.isSuccessful || newToken.body() == null) {
               tokenManager.deleteToken()
            }

            newToken.body()?.let {
               tokenManager.saveToken(it.token)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.token}")
                    .build()
            }
        }
    }

    private suspend fun getNewToken(refreshToken: String?, user:UserDTO): retrofit2.Response<LoginResponse> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        var gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://runninglist.310ultraects.keenetic.pro")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        val service = retrofit.create(ApiUserServises::class.java)
        return service.LogIN(user)
    }
}