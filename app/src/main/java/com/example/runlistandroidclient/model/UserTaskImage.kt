package com.example.runlistandroidclient.model


class UserTaskImage constructor(
    var id: Int,
    var user: User,
    var userId: Int,
    var title:String,
    var description:String,
    var startDay: Int,
    var changeDay: Int,
    var difficulty: Int,
    var weekNumber: Int,
    var completed: Boolean,
    var changed: Boolean,
    var templateTask: Boolean,
    var Item : Array<Int?>,
){

}
