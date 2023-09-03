package com.example.runlistandroidclient.helpers

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.runlistandroidclient.R
import com.example.runlistandroidclient.model.Difficulty
import com.example.runlistandroidclient.model.User
import com.example.runlistandroidclient.model.UserTask
import com.example.runlistandroidclient.model.UserTaskImage
import java.time.DayOfWeek

@RequiresApi(Build.VERSION_CODES.O)
fun RefreshListTask(tasks :List<UserTask>) : List<UserTaskImage>
{
      val UserTaskImages : MutableList<UserTaskImage> = arrayListOf()
      val image = arrayListOf<Int>(R.drawable.empty,R.drawable.completed,R.drawable.next,R.drawable.easy,R.drawable.hard,R.drawable.medium)
      tasks.forEach{
          val task = it
          Log.d("CheakDate2" ,task.toString())
          val dayimegemass = arrayOfNulls<Int>(8)
          var startDay = task.startDay
          var endDay = task.changeDay
          if (task.startDay == DayOfWeek.SUNDAY.value)
          {
              startDay = 6
              endDay = 6
          }
          if (task.changeDay == 0)
          {
              endDay = 7
              startDay =7
          }
          for (i in 1..startDay)
          {
              dayimegemass[i] = image[0]

          }
          if (endDay <= 6)
          {
              for (i in endDay .. 7)
              {
                  dayimegemass[i] = image[0]

              }
          }
          if (startDay != endDay)
          {
              if (startDay < endDay)
              {
                  for (i in startDay .. endDay)
                  {
                      dayimegemass[i] = image[2]

                  }

              }
              else if(startDay > endDay)
              {
                  for (i in endDay .. startDay)
                  {
                      dayimegemass[i] = image[0] // добавить обрат. изображение
                  }
              }


              }
          if (task.completed)
          {
              dayimegemass[endDay] = image[1]
              Log.d("ceasgsgf","зашёл")
          }
          else
          {
              if (task.difficulty == 1)
              {
                  dayimegemass[endDay] = image[3]
              }
              else if (task.difficulty == 2)
              {
                  dayimegemass[endDay] = image[5]

              }
              else if (task.difficulty == 3)
              {
                  dayimegemass[endDay] = image[4]
              }

          }
          
          val UserTaskI = UserTaskImage(
              id =task.id,
              user = task.user,
              userId = task.userId,
              description = task.description,
              title = task.title,
              difficulty = task.difficulty,
              weekNumber = task.weekNumber,
              startDay = task.startDay,
              changed = task.changed,
              completed = task.completed,
              templateTask = task.templateTask,
              changeDay = task.changeDay,
              Item = dayimegemass)



          Log.d("CheakItem", UserTaskI.startDay.toString())
          UserTaskImages.add(UserTaskI)
      }

    return  UserTaskImages


}
fun refImageUserTasAndUserTask(userTaskImage: UserTaskImage) : UserTask{
    return  UserTask(id = userTaskImage.id,
        user = userTaskImage.user,
        userId = userTaskImage.userId,
        title = userTaskImage.title,
        description = userTaskImage.description,
        startDay = userTaskImage.startDay,
        changeDay = userTaskImage.changeDay,
        difficulty = userTaskImage.difficulty,
        weekNumber = userTaskImage.weekNumber,
        completed = userTaskImage.completed,
        templateTask = userTaskImage.templateTask,
        changed = userTaskImage.changed
        )
}