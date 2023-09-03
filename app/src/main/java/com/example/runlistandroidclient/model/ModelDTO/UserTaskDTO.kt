package com.example.runlistandroidclient.model.ModelDTO

import java.util.*


data class UserTaskDTO(
    var title: String,
    var description: String,
    var date: String,
    var starttDay: Int?,
    var changeDay: Int?,
    var difficulty: Int,
    var week: Int,
    var completed: Boolean,
    var changed: Boolean
)
