package com.example.runlistandroidclient.data.network

import com.example.runlistandroidclient.model.LoginResponse
import com.example.runlistandroidclient.model.ModelDTO.UserDTO
import com.example.runlistandroidclient.model.User
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiUserServises {
    @Headers("Content-Type: application/json")
    @POST("/api/User/login")
    suspend fun LogIN(@Body requestBody: UserDTO) : Response<LoginResponse>
    @POST("/api/User/registration")
    suspend fun Regist(@Body requestBody: UserDTO) : Response<User>





}