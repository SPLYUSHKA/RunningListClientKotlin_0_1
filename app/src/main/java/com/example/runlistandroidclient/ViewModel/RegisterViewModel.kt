package com.example.runlistandroidclient.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runlistandroidclient.data.network.ApiUserRepository
import com.example.runlistandroidclient.model.ModelDTO.UserDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: ApiUserRepository,
): ViewModel()
{
    private val _message = MutableLiveData<String>()
    val message : LiveData<String>
        get() =_message
    private val _iseneble = MutableLiveData<Boolean>()
    val IsEneble : LiveData<Boolean>
        get() =_iseneble

   fun registPeopople(newuser : UserDTO)
   {
       viewModelScope.launch {


           if(newuser.email != null && newuser.name != null && newuser.password != null) {
              if(getEmailAddressesInString(newuser.email)!!.size != 0){
                 /* _message.value ="Регистрация прошла успешно.Переходите на экран входа в приложение"
                  authRepository.reg(newuser)*/

                  if(isValidPassword(newuser.password)) {

                        var s  = authRepository.reg(newuser)

                        if( s.code()!! != 400){

                           _message.value ="Регистрация прошла успешно.Переходите на экран входа в приложение"
                        }
                        else{
                            _message.value ="Ошибка.Такая почта уже есть в системе"
                        }
                      }

                      else {
                         _message.value ="Некорректный пароль"
                      }
               }
               else
               {
                   _message.value ="Некорректная почта"

               }
           }
           else
           {

               _message.value = "Все поля должны быть заполнены!"

           }

       }

   }
    fun getEmailAddressesInString(text: String): ArrayList<String>? {
        val emails: ArrayList<String> = ArrayList()
        val matcher =
            Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")
                .matcher(text)
        while (matcher.find()) {
            emails.add(matcher.group())
        }
        return emails
    }
    fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN = "[A-Z0-9a-z]{7,49}"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }

}