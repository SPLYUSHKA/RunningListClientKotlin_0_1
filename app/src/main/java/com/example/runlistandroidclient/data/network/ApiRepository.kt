package com.example.runlistandroidclient.data.network

import com.example.runlistandroidclient.helpers.apiRequestFlow
import com.example.runlistandroidclient.model.Difficulty
import com.example.runlistandroidclient.model.ModelDTO.UserDTO
import com.example.runlistandroidclient.model.ModelDTO.UserTaskDTO
import com.example.runlistandroidclient.model.UserTask
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiServises: ApiServises) {

    suspend fun getAllTask(weeknumber : Int)  = apiServises.getAllCurrentWeekNumberTask(weeknumber)
    suspend fun edittaskdif(difficulty :Int,task : UserTask) =   apiServises.editTaskDiff(difficulty,task)
    suspend fun edittaskmove(operations :Int,task : UserTask) = apiServises.editTaskMove(operations,task)
    suspend fun deletetask(task :UserTask) = apiServises.deleteTask(task)
    suspend fun addtask(template :Boolean, taskDTO: UserTaskDTO) = apiServises.AddTask(templete =false,taskDTO)
    suspend fun getCompletedTask(weeknumber: Int) = apiServises.getCompletedCurrentWeekNumberTask(weeknumber)
}