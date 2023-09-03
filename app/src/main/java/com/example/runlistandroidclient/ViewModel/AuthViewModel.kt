package com.example.runlistandroidclient.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.runlistandroidclient.data.network.ApiUserRepository
import com.example.runlistandroidclient.helpers.ApiResponse
import com.example.runlistandroidclient.model.LoginResponse
import com.example.runlistandroidclient.model.ModelDTO.UserDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: ApiUserRepository,
): BaseViewModel() {



    private val _loginResponse = MutableLiveData<ApiResponse<LoginResponse>>()
    val loginResponse : LiveData<ApiResponse<LoginResponse>> = _loginResponse

    fun login(user: UserDTO, coroutinesErrorHandler: CoroutinesErrorHandler) = baseRequest(
        _loginResponse,
        coroutinesErrorHandler
    ) {

        authRepository.LogIN(userDTO = user)
    }




}