package com.example.runlistandroidclient.ViewModel


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runlistandroidclient.data.network.ApiServises
import com.example.runlistandroidclient.helpers.define
import com.example.runlistandroidclient.helpers.defineday
import com.example.runlistandroidclient.model.ModelDTO.UserTaskDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import javax.inject.Inject
import java.time.LocalDate
import java.util.*

@HiltViewModel
class AddViewModel @Inject constructor(private val repository: ApiServises) : ViewModel() 
{

    private val _day = MutableLiveData<Int>()
    val day : LiveData<Int>
        get() =_day

    private val _week = MutableLiveData<Int>()
    val week : LiveData<Int>
        get() =_week

    private val _date = MutableLiveData<LocalDate>()
    val date : LiveData<LocalDate>
        get() =_date


    private val _diff = MutableLiveData<Int>()
    val Diff : LiveData<Int>
        get() =_diff
    private val _isaddtask = MutableLiveData<Boolean>()
    val IsAddTask : LiveData<Boolean>
        get() =_isaddtask
    private val _message = MutableLiveData<String>()
    val message : LiveData<String>
        get() =_message
    fun getweeknumber(){
        viewModelScope.launch {
            _week.value  = define()
        }
    }


    fun plannextweek()
    {
        viewModelScope.launch {
            _week.value  = define()
            _week.value!!.plus(1)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun AddTasks(Day :String, Difficultly : String,title:String, Des : String){
        viewModelScope.launch {
            if (Difficultly != "" && Day != "" && title != "" && Des != "") {
                var datecorrect = defineday(LocalDate.now())
                defineday2(Day)
                definedif(Difficultly)

                if (datecorrect <= _day.value!! || _day.value!! == 0 && datecorrect == 7) {

                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val currentDate = sdf.format(Date())

                    var newTask = _diff.value?.let {
                        _week.value?.let { it1 ->
                            UserTaskDTO(
                                title = title,
                                description = Des,
                                date = currentDate,
                                starttDay = _day.value,
                                changeDay = _day.value,
                                difficulty = it,
                                week = it1,
                                completed = false,
                                changed = false
                            )
                        }
                    }
                    _message.value = "Задача успешно добавлена"
                    _isaddtask.value = true
                    newTask?.let { repository.AddTask(templete = false, it) }



            }
            else
            {

              _message.value ="Нельзя установить такой день для выполнения задачи."
                _isaddtask.value = false

            }
        }
            else {
                _message.value ="Поля для задачи не должны быть пустыми"
                _isaddtask.value = false
            }
        }
    }
    fun definedif(Difficultly: String)
    {
        when(Difficultly){
            "Easy" -> _diff.value =1
            "Medium" -> _diff.value =2
            "Hard" -> _diff.value =3

        }
    }
   fun defineday2(Day: String){
       when(Day){
           "Monday" -> _day.value = 1
           "Tuesday" -> _day.value = 2
           "Wednesday"-> _day.value =3
           "Thursday" ->_day.value =4
           "Friday" ->_day.value = 5
           "Saturday" ->_day.value = 6
           "Sunday" -> _day.value = 0
       }
   }
}