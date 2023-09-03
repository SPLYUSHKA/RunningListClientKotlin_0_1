package com.example.runlistandroidclient.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.CornerRadius.Companion.Zero
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Velocity.Companion.Zero
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.runlistandroidclient.R
import com.example.runlistandroidclient.ViewModel.AddViewModel
import com.example.runlistandroidclient.navigation.Screens


@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterialApi
@Composable
fun AddTaskScreen(navController: NavController,addViewModel: AddViewModel){
    val title = remember {
        mutableStateOf("")
    }
    val description = remember {
        mutableStateOf("")
    }
    val isaddtask = addViewModel.IsAddTask.observeAsState(false)
    val message = addViewModel.message.observeAsState("")
    val context = LocalContext.current
  /*  val onDismissSnackBarState by rememberUpdatedState(newValue =addViewModel.dismissSnackBar())
   val scaffoldState = rememberScaffoldState()
    if (addViewModel.isSnackBarShowing) {
        val snackBarMessage = message.value.toString()
        LaunchedEffect(addViewModel.isSnackBarShowing) {
            try {
                when (scaffoldState.snackbarHostState.showSnackbar(
                    snackBarMessage,
                )) {
                    SnackbarResult.Dismissed -> {

                      }
                    }
                }
            finally {
                onDismissSnackBarState
            }
        }

        }*/
    var expandeddifficulty = remember { mutableStateOf(false) }
    val suggestionsdifficulty = listOf("Easy","Medium","Hard")
    var selectedTextdifficulty = remember { mutableStateOf("") }

    var textfieldSizedifficulty = remember { mutableStateOf(Size(0f,0f))}

    val icon = if (expandeddifficulty.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.ArrowDropDown

    var expandedday = remember { mutableStateOf(false) }
    val suggestionsday= listOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    var selectedTextday = remember { mutableStateOf("") }

    var textfieldSizeday = remember { mutableStateOf(Size(0f,0f))}

    val iconday = if (expandedday.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.ArrowDropDown

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.addtaskback),
                contentScale = ContentScale.FillBounds
            )
    ){
        TextField(
            value = title.value,
            modifier =
            Modifier
                .padding(0.dp, 60.dp, 30.dp, 0.dp)
                .align(Alignment.CenterHorizontally)
                .size(width = 308.dp, height = 60.dp)
                .wrapContentWidth()
                .wrapContentHeight()
                  ,
            textStyle = TextStyle(
                fontSize = (18.sp),
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Monospace
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = colorResource(id = R.color.textFieldAddTask),
                disabledLabelColor = colorResource(id = R.color.login_text_f_1),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                placeholderColor = colorResource(id = R.color.textFieldAddTask),
                textColor = colorResource(id = R.color.textFieldAddTask)
            ),

            onValueChange = { newText -> title.value = newText },


            placeholder = {
                Text(
                    "Название задачи",
                    fontFamily = FontFamily.Monospace,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp
                )
            },
            shape = RoundedCornerShape(18.dp),
            maxLines = 5
        )
        TextField(
            value = description.value,
            modifier =
            Modifier
                .padding(0.dp, 0.dp, 45.dp, 0.dp)
                .align(Alignment.CenterHorizontally)
                .size(width = 308.dp, height = 80.dp)
                .wrapContentWidth()
                .wrapContentHeight(),
            textStyle = TextStyle(
                fontSize = (18.sp),
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Monospace
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = colorResource(id = R.color.textFieldAddTask),
                disabledLabelColor = colorResource(id = R.color.login_text_f_1),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                placeholderColor = colorResource(id = R.color.textFieldAddTask),
                textColor = colorResource(id = R.color.textFieldAddTask)
            ),

            onValueChange = { newText -> description.value = newText },


            placeholder = {
                Text(
                    "Описание задачи",
                    fontFamily = FontFamily.Monospace,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp
                )
            },
            shape = RoundedCornerShape(18.dp),
            maxLines = 10
        )
        Box() {
            OutlinedTextField(
                value = selectedTextdifficulty.value,
                onValueChange = { selectedTextdifficulty.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)

                    .onGloballyPositioned { coordinates ->

                        textfieldSizedifficulty.value = coordinates.size.toSize()
                    },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.White,
                    disabledLabelColor = colorResource(id = R.color.textFieldAddTask),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.Black,
                    textColor = colorResource(id = R.color.textFiledAddTask2)

                ),
                label = {Text("Важность задачи",fontStyle = FontStyle.Italic)},
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { expandeddifficulty.value = !expandeddifficulty.value })
                }
            )
            DropdownMenu(

                expanded = expandeddifficulty.value,
                onDismissRequest = { expandeddifficulty.value = false },
                modifier = Modifier
                    .background(Color.Transparent)
                    .width(with(LocalDensity.current) { textfieldSizedifficulty.value.width.toDp() })
            ) {
                suggestionsdifficulty.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedTextdifficulty.value = label
                    }) {
                        Text(text = label,fontStyle = FontStyle.Italic)
                    }
                }
            }
        }
        Box() {
            OutlinedTextField(
                value = selectedTextday.value,
                onValueChange = { selectedTextday.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)

                    .onGloballyPositioned { coordinates ->

                        textfieldSizeday.value = coordinates.size.toSize()
                    },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.White,
                    disabledLabelColor = colorResource(id = R.color.textFieldAddTask),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.Black,
                    textColor = colorResource(id = R.color.textFiledAddTask2)

                ),
                label = {Text("День недели",fontStyle = FontStyle.Italic)},
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { expandedday.value = !expandedday.value })
                }
            )
            DropdownMenu(

                expanded = expandedday.value,
                onDismissRequest = { expandedday.value = false },
                modifier = Modifier
                    .background(Color.Transparent)
                    .width(with(LocalDensity.current) { textfieldSizeday.value.width.toDp() })

            ) {
                suggestionsday.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedTextday.value = label
                    }) {
                        Text(text = label, fontStyle = FontStyle.Italic)
                    }
                }
            }
        }
       Row(modifier = Modifier
           .fillMaxWidth()
           .padding(0.dp, 140.dp, 0.dp, 0.dp))
       {
           ClickableText(
               text = AnnotatedString("Добавить задачу"),
               onClick = {
                   addViewModel.AddTasks(selectedTextday.value,selectedTextdifficulty.value,title.value, description.value)

                    if(message.value != "")
                    {
                        Toast.makeText(context,message.value,Toast.LENGTH_LONG).show()
                        if(isaddtask.value == true)
                        {
                            navController.navigate(Screens.Navigate.route)
                        }
                    }
               },
               modifier = Modifier
                   .padding(100.dp, 0.dp, 0.dp, 0.dp)
                   .size(220.dp, 60.dp)
                   .padding(8.dp)
               ,
               style = TextStyle(color = colorResource(id = R.color.textFieldAddTask), fontSize = 20.sp, fontWeight = FontWeight.Light, fontFamily = FontFamily.Monospace, fontStyle = FontStyle.Italic)
           )
           Image(modifier=Modifier.size(40.dp), bitmap = ImageBitmap.imageResource(id = R.drawable.spot), contentDescription = "spot")
          // addViewModel.showSnackBar()
          /* if(isaddtask.value)
           {
               navController.navigate(Screens.Main.route)
           }*/
       }
        Box(modifier = Modifier.padding(0.dp,100.dp,20.dp,0.dp)) {
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