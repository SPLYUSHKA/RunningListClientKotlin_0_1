package com.example.runlistandroidclient.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.runlistandroidclient.R
import com.example.runlistandroidclient.ViewModel.AddViewModel
import com.example.runlistandroidclient.ViewModel.MainViewModelTask
import com.example.runlistandroidclient.navigation.Screens

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigateScreen(navController: NavController,mainViewModelTask: MainViewModelTask,AddViewModel : AddViewModel){

    Surface() {
      Column(
          modifier = androidx.compose.ui.Modifier
              .fillMaxSize()
              .paint(
                  painter = painterResource(R.drawable.selectionscreenback),
                  contentScale = ContentScale.FillBounds
              )
      )
      {

         Image(
              painter = painterResource(id = R.drawable.navbuttoncurrentweek),
              contentDescription = null,
              modifier = Modifier
                  .padding(0.dp, 200.dp, 0.dp, 0.dp)
                  .fillMaxWidth()
                  .size(300.dp, 80.dp)
                  .clickable {
                      mainViewModelTask.getweeknumber()
                      mainViewModelTask.weeknumber.value?.let { mainViewModelTask.getAllTaskWeek(it) }
                      navController.navigate(Screens.Main.route)
                      }
                  .padding(30.dp, 0.dp, 0.dp, 0.dp)

          )


          Image(
              painter = painterResource(id = R.drawable.navaddtask), contentDescription = null,
              modifier = Modifier
                  .padding(30.dp, 0.dp, 30.dp, 0.dp)
                  .fillMaxWidth()
                  .size(300.dp, 80.dp)
                  .clickable {
                      AddViewModel.getweeknumber()
                      navController.navigate(Screens.AddTask.route) }
                  .align(Alignment.CenterHorizontally)


          )

          Image(
              painter = painterResource(id = R.drawable.navcalendarweek), contentDescription = null,
              modifier = Modifier
                  .padding(30.dp, 0.dp, 30.dp, 0.dp)
                  .fillMaxWidth()
                  .size(300.dp, 80.dp)
                  .clickable {
                      navController.navigate(Screens.Login.route)
                  }
                  .align(Alignment.CenterHorizontally)


          )

      }
    }


}
