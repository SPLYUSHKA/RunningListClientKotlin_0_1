package com.example.runlistandroidclient.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runlistandroidclient.data.network.datemang.TokenManager
import com.example.runlistandroidclient.model.ModelDTO.UserDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val tokenManager: TokenManager,
): ViewModel() {

   val email = MutableLiveData<String?>()
   val password = MutableLiveData<String?>()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            tokenManager.getUserPassword().collect{
                withContext(Dispatchers.Main){
                    password.value = it
                }
            }
           tokenManager.getUserEmail().collect {
                withContext(Dispatchers.Main) {
                    email.value = it
                }
            }

        }
    }

    fun saveUser(email :String,password :String) {
        viewModelScope.launch(Dispatchers.IO) {
            tokenManager.saveUser(email, password)
        }
    }

    fun deleteUser() {
        viewModelScope.launch(Dispatchers.IO) {
            tokenManager.deleteUser()
        }
    }
}