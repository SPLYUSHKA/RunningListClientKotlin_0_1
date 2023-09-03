package com.example.runlistandroidclient.ViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runlistandroidclient.data.network.ApiServises
import com.example.runlistandroidclient.helpers.defineday
import com.example.runlistandroidclient.helpers.refImageUserTasAndUserTask
import com.example.runlistandroidclient.model.Difficulty
import com.example.runlistandroidclient.model.UserTask
import com.example.runlistandroidclient.model.UserTaskImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
@HiltViewModel
class EditTaskViewModel @Inject constructor(private val repository: ApiServises) : ViewModel() {
    private val _task = MutableLiveData<UserTask>()
    val task : LiveData<UserTask>
        get() =_task
    private val _title = MutableLiveData<String>()
    val title : LiveData<String>
        get() =_title
    private val _description = MutableLiveData<String>()
    val des : LiveData<String>
        get() =_description
    private val _day = MutableLiveData<Int>()
    val day : LiveData<Int>
        get() =_day
    private val _completed = MutableLiveData<String>()
    val completed : LiveData<String>
        get() =_completed
    private val _diffnow = MutableLiveData<Difficulty>()
    val DifNow : LiveData<Difficulty>
        get() =_diffnow

    private val _diff = MutableLiveData<Int>()
    val Diff : LiveData<Int>
        get() =_diff
    private val _cg = MutableLiveData<Boolean>()
    val Cg: LiveData<Boolean>
        get() =_cg
    private val _op = MutableLiveData<Int>()
    val Opper : LiveData<Int>
         get() = _op
    private val _dayName = MutableLiveData<String>()
    val DayName : LiveData<String>
        get() = _dayName
    private val _message = MutableLiveData<String>()
    val message : LiveData<String>
        get() =_message
    fun refres(task: UserTaskImage)
    {
        viewModelScope.launch {
            _task.value = refImageUserTasAndUserTask(task)
            _title.value = _task.value!!.title.toString()
            _description.value = _task.value!!.description.toString()
            _cg.value =_task.value!!.changed
            when (_task.value!!.difficulty) {
                1 -> {
                    _diffnow.value =Difficulty.Easy
                }
                2 -> {
                    _diffnow.value =Difficulty.Medium
                }
                3 -> {
                    _diffnow.value =Difficulty.Hard
                }
            }
            if(_cg.value!!)
            {
                _day.value =_task.value!!.changeDay
                purpuseDay()
            }
            else{_day.value = _task.value!!.startDay}
            if(_task.value!!.completed)
            {
                _completed.value ="Завершена"

            }
            else{
                _completed.value ="Не завершена"
            }
            purpuseDay()
        }
    }
    fun purpuseDay(){
        when(_day.value)
        {
            1->_dayName.value ="Monday"
            2->_dayName.value ="Tuesday"
            3->_dayName.value ="Wednesday"
            4->_dayName.value ="Thursday"
            5->_dayName.value ="Friday"
            6->_dayName.value ="Saturday"
            0->_dayName.value ="Sunday"
            7->_dayName.value ="Sunday"

        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun deletetask(){

        if(_task.value?.completed != true) {
            viewModelScope.launch {
                //исправитииииииииииииииить
               var datecorrect = defineday(LocalDate.now())
                if(datecorrect == task.value!!.changeDay) {
                    task.value?.let { repository.deleteTask(it) }

                }
                else
                {
                    _message.value = "нельзя завершить эту задачу, т.к. день её выполнения ещё не наступил"
                }
            }
        }
    }
    fun editMove(op :Int)
    {
        if(_task.value!!.completed != true) {
        viewModelScope.launch {
            if(_day.value == 1 && op ==2)
            {
                _message.value ="Нельзя передвинуть задачу"
            }
            else if((_day.value == 7 || _day.value == 0) && op ==1)
            {
                _message.value ="Нельзя передвинуть задачу"
            }
            else {
                _op.value = op
                Opper.value?.let {
                    task.value?.let { it1 -> repository.editTaskMove(it, it1) }
                }
                _message.value ="Задача успешно изменена"
            }

         }
        }
        else
        {
            _message.value ="Эту задачу уже нельзя редактировать, т.к. она была завершена"
        }
    }
    fun editDiff(difint : Int){
        Log.d("LoglOG",DifNow.value.toString() + difint.toString())

            if ((DifNow.value != Difficulty.Easy && difint != -1) || (DifNow.value != Difficulty.Hard && difint != 1)) {

                viewModelScope.launch {
                    if (_task.value?.completed != true) {

                        if (DifNow.value == Difficulty.Hard && difint == -1) {
                            _diff.value = 2
                            _diffnow.value = Difficulty.Medium
                        } else if (difint == -1 && _diffnow.value == Difficulty.Medium) {

                            _diff.value = 1
                            _diffnow.value = Difficulty.Easy
                        } else if (DifNow.value == Difficulty.Easy && difint == 1) {
                            _diff.value = 2
                            _diffnow.value = Difficulty.Medium
                        } else if (DifNow.value == Difficulty.Medium && difint == 1) {
                            _diff.value = 3
                            _diffnow.value = Difficulty.Hard
                        }


                        Log.d("sdfsf", "${_task.value} ${_diff.value}")
                        Diff.value?.let {
                            task.value?.let { it1 ->
                                repository.editTaskDiff(
                                    it,
                                    it1
                                )
                            }
                        }
                    }
                    _message.value ="Задача успешно изменена"
                }
            }
            if (DifNow.value == Difficulty.Easy && difint == 1) {
                viewModelScope.launch {
                    _diff.value = 2
                    _diffnow.value = Difficulty.Medium
                    Diff.value?.let { task.value?.let { it1 -> repository.editTaskDiff(it, it1) } }
                }
                _message.value ="Задача успешно изменена"
            }
            if (DifNow.value == Difficulty.Hard && difint == -1) {
                viewModelScope.launch {
                    _diff.value = 2
                    _diffnow.value = Difficulty.Medium
                    Diff.value?.let { task.value?.let { it1 -> repository.editTaskDiff(it, it1) } }
                }
                _message.value ="Задача успешно изменена"
            }


    }
}