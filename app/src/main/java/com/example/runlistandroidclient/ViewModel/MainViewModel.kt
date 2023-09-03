package com.example.runlistandroidclient.ViewModel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runlistandroidclient.data.network.ApiRepository
import com.example.runlistandroidclient.data.network.ApiUserServises
import com.example.runlistandroidclient.model.ModelDTO.UserDTO
import com.example.runlistandroidclient.model.UserTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ApiUserServises) : ViewModel()
{
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    fun setEmail(email: String) {
        _email.value = email
    }
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun setPassword(password: String) {
        _password.value = password
    }


    fun postLogin(){

            viewModelScope.launch {
                 repository.LogIN(UserDTO(name = "  ", email = _email.value, password = _password.value)).let{
                     if(it.isSuccessful)
                     {
                         Log.d("CheakDate" ,it.body().toString())
                     }
                     else
                     {
                         Log.d("CheakDate",it.body().toString())
                     }
                }
            }

    }
}
