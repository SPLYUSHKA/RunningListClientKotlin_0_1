package com.example.runlistandroidclient.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.runlistandroidclient.R
import com.example.runlistandroidclient.ViewModel.CoroutinesErrorHandler
import com.example.runlistandroidclient.ViewModel.RegisterViewModel
import com.example.runlistandroidclient.model.ModelDTO.UserDTO
import com.example.runlistandroidclient.navigation.Screens
import java.lang.reflect.Modifier

@Composable
fun RegistrScreen(navController: NavController, RegistViewModel : RegisterViewModel){

    val email = remember {
        mutableStateOf("")
    }
    val name = remember {
        mutableStateOf("")
    }
    val passwordFirst = remember {
        mutableStateOf("")
    }
    val passwordSecond = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    var message =RegistViewModel.message.observeAsState("")
    Surface() {
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colorStops = arrayOf(
                            0.0f to colorResource(R.color.back_1_splash_screen),
                            0.3f to colorResource(R.color.back_2_splash_screen),
                            0.5f to colorResource(R.color.back_3_splash_screen),
                            0.8f to colorResource(R.color.back_4_splash_screen),
                            1f to colorResource(R.color.back_5_splash_screen)

                        )
                    )
                )
        )
        {
            Text(
                text ="New \n" +
                        "Account ",
                textAlign = TextAlign.Start,
                color = Color.White,
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .padding(18.dp, 0.dp, 0.dp, 0.dp)
                ,
                style = MaterialTheme.typography.h4.copy(
                    shadow = Shadow(
                        color = colorResource(id = R.color.black_alpha_25) ,
                        offset = Offset(x = 0f, y = 4f),
                        blurRadius = 0.4f,

                        )
                ),

                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            )
            TextField(
                value = email.value,
                modifier =
                androidx.compose.ui.Modifier
                    .padding(0.dp, 70.dp, 0.dp, 0.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(width = 308.dp, height = 55.dp)
                    .border(BorderStroke(1.dp, Color.White), RoundedCornerShape(15.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = R.color.login_text_f_2),
                    cursorColor = Color.White,
                    disabledLabelColor = colorResource(id = R.color.login_text_f_1),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor =Color.White,
                    textColor = Color.White
                ),
                label = {Text("Email")},
                onValueChange = {newText -> email.value = newText},
                shape = RoundedCornerShape(18.dp),
            )
            TextField(
                value = name.value,
                modifier =
                androidx.compose.ui.Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(width = 308.dp, height = 55.dp)
                    .border(BorderStroke(1.dp, Color.White), RoundedCornerShape(15.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = R.color.login_text_f_2),
                    cursorColor = Color.White,
                    disabledLabelColor = colorResource(id = R.color.login_text_f_1),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor =Color.White,
                    textColor = Color.White
                ),
                label = {Text("UserName")},
                onValueChange = {newText -> name.value = newText},
                shape = RoundedCornerShape(18.dp),
            )
            TextField(
                value = passwordFirst.value,
                modifier =
                androidx.compose.ui.Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(width = 308.dp, height = 55.dp)
                    .border(BorderStroke(1.dp, Color.White), RoundedCornerShape(15.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = R.color.login_text_f_2),
                    cursorColor = Color.White,
                    disabledLabelColor = colorResource(id = R.color.login_text_f_1),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor =Color.White,
                    textColor = Color.White
                ),
                label = {Text("Password")},
                onValueChange = {newText -> passwordFirst.value = newText},
                shape = RoundedCornerShape(18.dp),

            )
            TextField(
                value = passwordSecond.value,
                modifier =
                androidx.compose.ui.Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(width = 308.dp, height = 55.dp)
                    .border(BorderStroke(1.dp, Color.White), RoundedCornerShape(15.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = R.color.login_text_f_2),
                    cursorColor = Color.White,
                    disabledLabelColor = colorResource(id = R.color.login_text_f_1),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor =Color.White,
                    textColor = Color.White
                ),
                label = {Text("Repeat Password")},
                onValueChange = {newText -> passwordSecond.value = newText},
                shape = RoundedCornerShape(18.dp),

                )
            Button(
                onClick = {
                    if(name.value =="" || email.value == ""||passwordFirst.value ==""){
                        Toast.makeText(context,"Все поля должны быть заполнены", Toast.LENGTH_LONG).show()
                    }
                    else {
                        if (passwordFirst.value == passwordSecond.value) {
                            RegistViewModel.registPeopople(
                                UserDTO(
                                    name.value,
                                    email.value,
                                    passwordFirst.value
                                )
                            )
                        }
                        else
                        {
                            Toast.makeText(context,"Пароли не совпадают", Toast.LENGTH_LONG).show()
                        }

                        if (message.value != "") {
                            Toast.makeText(context, message.value, Toast.LENGTH_LONG).show()
                            /* if(isaddtask.value)
                         {
                             navController.navigate(Screens.Main.route)
                         }*/
                        }
                    }
                },
                modifier = androidx.compose.ui.Modifier
                    .padding(25.dp)
                    .size(400.dp, 73.dp)
                    .padding(5.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.buttonLogin),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(15.dp),
                content = { Text("Sign in") },

                )
            ClickableText(
                text = AnnotatedString("About System"),
                onClick = {
                    navController.navigate(Screens.AboutSystem.route)
                },
                modifier = androidx.compose.ui.Modifier

                    .padding(120.dp, 0.dp, 0.dp, 0.dp)
                    .size(400.dp, 30.dp)

                ,
                style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
            )
            ClickableText(
                text = AnnotatedString("Not the first time here? Log in"),
                onClick = {
                    navController.navigate(Screens.Login.route)
                },
                modifier = androidx.compose.ui.Modifier
                    .fillMaxHeight(30f)
                    .padding(45.dp, 40.dp, 0.dp, 0.dp)
                    .size(400.dp, 30.dp)

                ,
                style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            )
        }

    }

}