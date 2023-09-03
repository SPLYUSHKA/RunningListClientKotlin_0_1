package com.example.runlistandroidclient.data.network

import com.example.runlistandroidclient.helpers.apiRequestFlow
import com.example.runlistandroidclient.model.ModelDTO.UserDTO
import javax.inject.Inject

class ApiUserRepository @Inject constructor(private val apiUserServises: ApiUserServises ) {
    fun LogIN(userDTO: UserDTO) = apiRequestFlow {
        apiUserServises.LogIN(userDTO)
    }
    suspend fun  reg(userDTO: UserDTO) = apiUserServises.Regist(userDTO)

}