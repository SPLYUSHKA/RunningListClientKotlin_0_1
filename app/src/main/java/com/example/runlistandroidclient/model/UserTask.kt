package com.example.runlistandroidclient.model

import android.text.BoringLayout
import java.time.DayOfWeek
import java.util.*

data class UserTask(
    var id : Int,
    var user : User,
    var userId : Int,
    var title :String,
    var description :String,
    var startDay : Int,
    var changeDay : Int,
    var difficulty : Int,
    var weekNumber : Int,
    var completed : Boolean,
    var changed : Boolean,
    var templateTask : Boolean
)
