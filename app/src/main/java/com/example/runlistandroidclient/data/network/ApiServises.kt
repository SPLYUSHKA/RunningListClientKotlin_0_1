package com.example.runlistandroidclient.data.network

import com.example.runlistandroidclient.model.Difficulty
import com.example.runlistandroidclient.model.ModelDTO.UserDTO
import com.example.runlistandroidclient.model.ModelDTO.UserTaskDTO
import com.example.runlistandroidclient.model.UserTask
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiServises {
    @Headers("Content-Type: application/json")
    @GET("/api/RunningList/GetAllTaskWeekNumber")
    suspend fun getAllCurrentWeekNumberTask(@Query("weeknumer") weeknumber :Int): Response<List<UserTask>>
    @POST("api/RunningList/EditTaskDif")
    suspend fun  editTaskDiff(@Query("difficulty") difficulty : Int,@Body requestBody: UserTask)
    @POST("api/RunningList/EditTaskMove")
    suspend fun  editTaskMove(@Query("operations") operations : Int,@Body requestBody: UserTask)
    @POST("api/RunningList/DeleteTask")
    suspend fun deleteTask(@Body requestBody: UserTask)
    @POST("api/RunningList/AddTask")
    suspend fun AddTask(@Query("templete") templete :Boolean,@Body requestBody: UserTaskDTO)

    @GET("/api/RunningList/GetAllTaskWeekNumberCompleted")
    suspend fun getCompletedCurrentWeekNumberTask(@Query("weeknumer") weeknumber :Int): Response<List<UserTask>>
}