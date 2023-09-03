package com.example.runlistandroidclient.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.runlistandroidclient.R
import com.example.runlistandroidclient.ViewModel.EditTaskViewModel
import com.example.runlistandroidclient.navigation.Screens


@Composable
fun EditTaskScreen(navController: NavController,editTaskViewModel: EditTaskViewModel)
{ 
    val task = editTaskViewModel.task.observeAsState(initial = "")
    val title = editTaskViewModel.title.observeAsState("")
    val day = editTaskViewModel.day.observeAsState("")
    val dayName = editTaskViewModel.DayName.observeAsState("")
    val descript = editTaskViewModel.des.observeAsState("")
    val completed = editTaskViewModel.completed.observeAsState("")
    val difnow = editTaskViewModel.DifNow.observeAsState("")
    val message = editTaskViewModel.message.observeAsState("")
    val context = LocalContext.current
  Column(modifier = Modifier
      .fillMaxSize()
      .paint(
          painter = painterResource(R.drawable.addtaskback), contentScale = ContentScale.FillBounds
      )
  ){
     Text(
         text = title.value.toString(),
         modifier = Modifier
             .padding(0.dp, 60.dp, 30.dp, 0.dp)
             .align(Alignment.CenterHorizontally)
             .size(width = 308.dp, height = 60.dp)
             .wrapContentWidth()
             .wrapContentHeight(),
         fontSize = (18.sp),
         fontStyle = FontStyle.Italic,
         fontFamily = FontFamily.Monospace

     )
      Text(
          text = descript.value.toString(),
          modifier = Modifier
              .padding(0.dp, 0.dp, 30.dp, 0.dp)
              .align(Alignment.CenterHorizontally)
              .size(width = 308.dp, height = 60.dp)
              .wrapContentWidth()
              .wrapContentHeight(),
          fontSize = (18.sp),
          fontStyle = FontStyle.Italic,
          fontFamily = FontFamily.Monospace

      )
      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Center) {

          Box(modifier = Modifier.padding(0.dp,5.dp,0.dp,0.dp)) {
              Image(painter = painterResource(id = R.drawable.downarrow), contentDescription = null,
                  modifier = Modifier
                      .size(34.dp)
                      .clickable
                      {
                        editTaskViewModel.editDiff(-1)
                      })
          }
          Text(
              text =difnow.value.toString(),
              modifier = Modifier
                  .padding(0.dp, 0.dp, 30.dp, 0.dp)
                  .size(width = 200.dp, height = 60.dp)
                  .wrapContentWidth()
                  .wrapContentHeight(),
              fontSize = (18.sp),
              fontStyle = FontStyle.Italic,
              fontFamily = FontFamily.Monospace

          )
          Box() {
              Image(painter = painterResource(id = R.drawable.uparrows), contentDescription = null,
                  modifier = Modifier
                      .size(34.dp)
                      .clickable
                      {
                      editTaskViewModel.editDiff(1)

                      })
          }

      }
      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Center) {

          Box(modifier = Modifier.padding(0.dp,5.dp,0.dp,0.dp)) {
              Image(painter = painterResource(id = R.drawable.downarrow), contentDescription = null,
                  modifier = Modifier
                      .size(34.dp)
                      .clickable
                      {
                          editTaskViewModel.editMove(2)
                          if(message.value != "")
                          {
                              Toast.makeText(context,message.value, Toast.LENGTH_LONG).show()

                          }
                      })
          }
          Text(
              text =dayName.value.toString(),
              modifier = Modifier
                  .padding(0.dp, 0.dp, 30.dp, 0.dp)
                  .size(width = 200.dp, height = 60.dp)
                  .wrapContentWidth()
                  .wrapContentHeight(),
              fontSize = (18.sp),
              fontStyle = FontStyle.Italic,
              fontFamily = FontFamily.Monospace

          )
          Box() {
              Image(painter = painterResource(id = R.drawable.uparrows), contentDescription = null,
                  modifier = Modifier
                      .size(34.dp)
                      .clickable
                      {
                          editTaskViewModel.editMove(1)
                          if(message.value != "")
                          {
                              Toast.makeText(context,message.value,Toast.LENGTH_LONG).show()

                          }

                      })
          }

      }
      Row(modifier = Modifier.fillMaxWidth()) {
          Text(
              text =completed.value,
              modifier = Modifier
                  .padding(0.dp, 0.dp, 0.dp, 0.dp)
                  .size(width = 308.dp, height = 60.dp)
                  .wrapContentWidth()
                  .wrapContentHeight(),
              fontSize = (18.sp),
              fontStyle = FontStyle.Italic,
              fontFamily = FontFamily.Monospace

          )

          Box() {
              Image(painter = painterResource(id = R.drawable.endtaskeditscreeen), contentDescription = null,
                  modifier = Modifier
                      .size(34.dp)
                      .clickable
                      {
                          editTaskViewModel.deletetask()
                          if(message.value != "")
                          {
                              Toast.makeText(context,message.value,Toast.LENGTH_LONG).show()

                          }

                      })
          }
      }

      Box(modifier = Modifier.padding(0.dp,250.dp,20.dp,0.dp)) {
          Image(painter = painterResource(id = R.drawable.backbutton), contentDescription = null,
              modifier = Modifier
                  .padding(0.dp,0.dp,30.dp,0.dp)
                  .size(100.dp)
                  .padding(0.dp,0.dp,30.dp,0.dp)
                  .clickable
                  {

                      navController.navigate(Screens.Navigate.route)
                  })
      }
 }
}

