package com.example.runlistandroidclient.ViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runlistandroidclient.data.network.ApiServises
import com.example.runlistandroidclient.helpers.RefreshListTask
import com.example.runlistandroidclient.helpers.define
import com.example.runlistandroidclient.model.UserTask
import com.example.runlistandroidclient.model.UserTaskImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModelTask @Inject constructor(private val repository: ApiServises) : ViewModel()
{
    private val _message = MutableLiveData<String>()
    val message : LiveData<String>
        get() = _message
    private val _contentButton = MutableLiveData<String>()
    val contentButton : LiveData<String>
        get() = _contentButton

    private val _listtask = MutableLiveData<List<UserTask>>()
    val Listtask : LiveData<List<UserTask>>
            get() =_listtask

    private val _weeknumber = MutableLiveData<Int>()
    val weeknumber : LiveData<Int>
        get() =_weeknumber

    private val _listtaskImage = MutableLiveData<List<UserTaskImage>>()
    val ListtaskImage : LiveData<List<UserTaskImage>>
        get() =_listtaskImage
    fun setList(tasks : List<UserTask>) {
        _listtask.value = tasks
    }
    fun setListImage(tasks : List<UserTaskImage>) {
        _listtaskImage.value = tasks
    }
    fun getweeknumber(){
        viewModelScope.launch {
           _weeknumber.value  = define()
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllTaskWeek(week : Int){

        _contentButton.value ="Завершённые"
        viewModelScope.launch {
            repository.getAllCurrentWeekNumberTask(week).let{
                if(it.isSuccessful)
                {
                    it.body()?.let { it1 -> setList(it1) }



                    _listtask.value?.let { it1 -> RefreshListTask(it1) }
                        ?.let { it2 -> setListImage(it2) }


                }
                else
                {
                   _message.value ="Не удалось загурзить данные"
                }
            }
        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCompletedTaskWeek(week : Int){
        _contentButton.value ="Все"
        viewModelScope.launch {

            repository.getCompletedCurrentWeekNumberTask(week).let{
                if(it.isSuccessful)
                {
                    it.body()?.let { it1 -> setList(it1) }



                    _listtask.value?.let { it1 -> RefreshListTask(it1) }
                        ?.let { it2 -> setListImage(it2) }



                }
                else
                {
                    _message.value ="Не удалось загурзить данные"
                }
            }
        }

    }
}


