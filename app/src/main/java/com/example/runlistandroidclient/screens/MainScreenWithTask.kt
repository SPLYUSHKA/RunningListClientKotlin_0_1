package com.example.runlistandroidclient.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.runlistandroidclient.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.runlistandroidclient.ViewModel.EditTaskViewModel
import com.example.runlistandroidclient.ViewModel.MainViewModelTask
import com.example.runlistandroidclient.model.UserTaskImage
import com.example.runlistandroidclient.navigation.Screens

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreenWithTask(navController: NavController, mainViewModelTask: MainViewModelTask,edittaskmodel :EditTaskViewModel){
    val allTask = mainViewModelTask.ListtaskImage.observeAsState(listOf()).value
    val weeknumer = mainViewModelTask.weeknumber.observeAsState(14)

   val contentD = mainViewModelTask.contentButton.observeAsState("Завершённые")

    Column(

        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.mainback),
                contentScale = ContentScale.FillBounds
            )
    ){

        Row(
            modifier = Modifier
                .size(600.dp, 65.dp)
                .paint(
                    painter = painterResource(id = R.drawable.headermain),
                    contentScale = ContentScale.FillBounds
                )
        ) {


        }
        Column(modifier = Modifier.weight(3f)) {
            LazyColumn{
                items(allTask.take(10)){item -> ItemListTemplate(element = item,navController,edittaskmodel)
                }
            }
        }
        Column(modifier = Modifier.weight(1.5f))
        {
            Button(
                onClick = {
                    if(contentD.value =="Завершённые") {
                        mainViewModelTask.getCompletedTaskWeek(weeknumer.value)
                    }
                    else if(contentD.value =="Все")
                    {
                        mainViewModelTask.getAllTaskWeek(weeknumer.value)
                    }

                },
                modifier = Modifier
                    .padding(240.dp, 45.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .size(70.dp, 65.dp)
                    .border(1.dp, color = Color.Black, shape = RoundedCornerShape(15.dp))
                ,
                colors = ButtonDefaults.buttonColors(

                    backgroundColor = colorResource(id = R.color.buttonbackMain),
                    contentColor = Color.White

                ),
                shape = RoundedCornerShape(15.dp),
                content = {
                    Text(
                        text = contentD.value,
                        style = MaterialTheme.typography.h4.copy(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(x = 0f, y = 2f),
                                blurRadius = 0.2f,

                                )
                        ),

                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 12.sp
                    )
                },

                )
            Button(
                onClick = {
                    navController.navigate(Screens.Navigate.route)
                },
                modifier = Modifier
                    .padding(0.dp, 10.dp, 220.dp, 0.dp)
                    .fillMaxWidth()
                    .size(120.dp, 40.dp)
                    .border(1.dp, color = Color.Black)
                ,
                colors = ButtonDefaults.buttonColors(

                    backgroundColor = colorResource(id = R.color.buttonmain),
                    contentColor = Color.White

                ),
                content = {
                    Text(
                        "Назад",
                        style = MaterialTheme.typography.h4.copy(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(x = 0f, y = 2f),
                                blurRadius = 0.2f,

                                )
                        ),

                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 17.sp
                    )
                },

                )
        }



    }
}



@Composable
fun ItemListTemplate(element: UserTaskImage, navController: NavController,edittaskmodel: EditTaskViewModel){
    val context = LocalContext.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(110.dp)
        .paint(
            painter = painterResource(id = R.drawable.tree),
            contentScale = ContentScale.FillBounds
        )){
        element.Item[0]?.let { painterResource(id = it) }
            ?.let { Image(painter = it, contentDescription ="" , modifier = Modifier.size(40.dp,50.dp)) }
        element.Item[1]?.let { painterResource(id = it) }
            ?.let { Image(painter = it, contentDescription ="" , modifier = Modifier.size(40.dp,50.dp)) }
        element.Item[2]?.let { painterResource(id = it) }
            ?.let { Image(painter = it, contentDescription ="", modifier = Modifier.size(40.dp,50.dp)) }
        element.Item[3]?.let { painterResource(id = it) }
            ?.let { Image(painter = it, contentDescription ="" , modifier = Modifier.size(40.dp,50.dp)) }
        element.Item[4]?.let { painterResource(id = it) }
            ?.let { Image(painter = it, contentDescription ="" , modifier = Modifier.size(40.dp,50.dp)) }
        element.Item[5]?.let { painterResource(id = it) }
            ?.let { Image(painter = it, contentDescription ="" , modifier = Modifier.size(40.dp,50.dp)) }
        element.Item[6]?.let { painterResource(id = it) }
            ?.let { Image(painter = it, contentDescription ="" , modifier = Modifier.size(40.dp,50.dp)) }
        element.Item[7]?.let { painterResource(id = it) }
            ?.let { Image(painter = it, contentDescription ="" , modifier = Modifier.size(40.dp,50.dp)) }
        Column() {
            Text(text = element.title, color = Color.White, modifier = Modifier.padding(2.dp,0.dp))

            Box() {
                Image(painter = painterResource(id = R.drawable.edit), contentDescription = null,
                    modifier = Modifier
                        .size(34.dp)
                        .clickable
                        {
                            if(element.difficulty ==1){
                                Toast.makeText(context,"Nunquam retrorsum, semper ingrediendum\nНи шагу назад, всегда вперед.", Toast.LENGTH_LONG).show()
                                Toast.makeText(context,"Ни шагу назад, всегда вперед.", Toast.LENGTH_LONG).show()
                            }
                            else if(element.difficulty ==2)
                            {
                                Toast.makeText(context,"Dictum factum\nСказано — сделано", Toast.LENGTH_LONG).show()
                            }
                            else
                            {
                                Toast.makeText(context,"Vincere aut mori.\nИли победить, или умереть.", Toast.LENGTH_LONG).show()
                            }

                            edittaskmodel.refres(element)
                            navController.navigate(Screens.EditTask.route)
                        })
            }
          /*  Button(
                modifier = Modifier
                    .padding(10.dp, 10.dp)
                    .size(100.dp, 50.dp),
                onClick = { *//*TODO*//* },content = {
                    Text(
                        "Редактировать",
                        style = MaterialTheme.typography.h4.copy(
                            shadow = Shadow(
                                color = Color.Gray,
                                offset = Offset(x = 0f, y = 2f),
                                blurRadius = 0.2f,

                                )
                        ),
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 15.sp
                    )
                }

            )*/


        }


    }

}
