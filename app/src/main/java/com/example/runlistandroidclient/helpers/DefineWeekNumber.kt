package com.example.runlistandroidclient.helpers

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*


fun define() : Int
{
    var input : String = "20130507";
    var format : String = "yyyyMMdd";
    val df = SimpleDateFormat(format)
    val date: Date = Date()
    val cal: Calendar = Calendar.getInstance()
    cal.setTime(date)

    val week: Int = cal.get(Calendar.WEEK_OF_YEAR)
    Log.d("Dateweek",week.toString())
    return week
}
@RequiresApi(Build.VERSION_CODES.O)
fun defineday(date: LocalDate) : Int
{
    val day: DayOfWeek = date.dayOfWeek
    return day.value
}

fun toDate(str :String) : Date?{
    var parser = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
    parser.timeZone = TimeZone.getDefault()
    return if(str != null)
    {
        parser.parse(str)
    }
    else Date()
}

