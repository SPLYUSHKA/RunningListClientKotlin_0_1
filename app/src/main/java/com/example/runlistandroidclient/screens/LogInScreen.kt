package com.example.runlistandroidclient.screens


import android.text.Layout.Alignment
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.runlistandroidclient.R
import com.example.runlistandroidclient.ViewModel.*
import com.example.runlistandroidclient.helpers.ApiResponse
import com.example.runlistandroidclient.model.LoginResponse
import com.example.runlistandroidclient.model.ModelDTO.UserDTO
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.runlistandroidclient.navigation.Screens
import com.example.runlistandroidclient.ui.theme.Brow

@Composable
fun LogInScreen(navController: NavController,viewModel: MainViewModel,authViewModel: AuthViewModel,tokenViewModel: TokenViewModel,userViewModel: UserViewModel) {


   val email = remember {
      mutableStateOf("")
   }
   val password = remember {
      mutableStateOf("")
   }
   val loginTV = remember {
      mutableStateOf("")
   }
   val checkedState = remember { mutableStateOf(false) }
   val context = LocalContext.current

   var token = tokenViewModel.token.observeAsState("")

   if (token.value != null) {
    //   navController.navigate(Screens.Navigate.route)
      //ENOTIKHA_PRODUCTION
      tokenViewModel.deleteToken()

   }

   var authstate = authViewModel.loginResponse.observeAsState(null)

   when (authstate.value) {
      is ApiResponse.Failure -> loginTV.value = authstate.value.toString()
      ApiResponse.Loading -> loginTV.value = authstate.value.toString()
      is ApiResponse.Success -> {
        tokenViewModel.saveToken((authstate.value as ApiResponse.Success<LoginResponse>).data.token)
      userViewModel.saveUser(email.value,password.value)}

      }


   Surface(

   ) {
      Column(

         modifier = Modifier
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
      ) {

         Text(
            text ="Log into your account",
            textAlign = TextAlign.Start,
            color = Color.White,
            modifier = Modifier
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
            Modifier
               .padding(0.dp, 100.dp, 0.dp, 0.dp)
               .align(CenterHorizontally)
               .size(width = 308.dp, height = 55.dp)
               .border(BorderStroke(1.dp, Color.White), RoundedCornerShape(15.dp)),
                colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(id = R.color.login_text_f_2),
                cursorColor = Color.White,
                disabledLabelColor = colorResource(id = R.color.login_text_f_1),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                placeholderColor = Color.White,
                textColor = Color.White
            ),
            onValueChange = { newText -> email.value = newText },
            placeholder = { Text("Enter Email")},
            shape = RoundedCornerShape(18.dp),
         )
         TextField(
            modifier =
            Modifier
               .padding(0.dp, 30.dp, 0.dp, 0.dp)
               .align(CenterHorizontally)
               .size(width = 308.dp, height = 55.dp)
               .border(BorderStroke(1.dp, Color.White), RoundedCornerShape(15.dp)),
            colors = TextFieldDefaults.textFieldColors(
               backgroundColor = colorResource(id = R.color.login_text_f_2),
               cursorColor = Color.Black,
               disabledLabelColor = colorResource(id = R.color.login_text_f_1),
               focusedIndicatorColor = Color.Transparent,
               unfocusedIndicatorColor = Color.Transparent,
               placeholderColor = Color.White,
               textColor = Color.White
            ),
            textStyle = TextStyle(color =Color.White,fontSize =20.sp),
            value = password.value,
            onValueChange = { newText -> password.value = newText },
            placeholder = { Text("Enter Password")},
            shape = RoundedCornerShape(18.dp),
            visualTransformation = PasswordVisualTransformation()
         )
       /*  Row(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 15.dp, 0.dp, 0.dp))
         {
           Checkbox(
              checked = checkedState.value,
              onCheckedChange = {checkedState.value = it},
              modifier =
              Modifier.size(20.dp,20.dp),
              colors = CheckboxDefaults.colors(
                 checkedColor = Color.White,
                 uncheckedColor = Color.White,
                 checkmarkColor = colorResource(id = R.color.CheakBox),
              )
            )
            Text(
               text ="Remember me",
               color = Color.White,
               modifier = Modifier
                  .padding(5.dp, 0.dp, 0.dp, 0.dp)
               ,
               fontWeight = FontWeight.Bold,
               fontSize = 16.sp
            )
            Text(
               text ="Forgot?",
               color = Color.White,
               modifier = Modifier
                  .padding(100.dp, 0.dp, 0.dp, 0.dp)

               ,
               fontWeight = FontWeight.Bold,
               fontSize = 16.sp
            )
         }*/
         Button(
            onClick = {
               authViewModel.login(
                  UserDTO("  ", email.value, password.value),
                  object : CoroutinesErrorHandler {
                     override fun onError(message: String) {

                        loginTV.value = "Error! $message"

                     }
                  }
               )
               if (token.value != null) {

                  navController.navigate(Screens.Navigate.route)
               }
               else {
                  if (loginTV.value == "Failure(errorMessage=Parameter specified as non-null is null: method kotlin.jvm.internal.Intrinsics.checkNotNullParameter, parameter errorMessage, code=400)" || loginTV.value == "") {
                     Toast.makeText(context, "Некорректные данные", Toast.LENGTH_LONG).show()

                  }
               }


            },
            modifier = Modifier
               .padding(25.dp)
               .size(400.dp, 73.dp)
               .padding(5.dp),
            colors = ButtonDefaults.buttonColors(
               backgroundColor = colorResource(id = R.color.buttonLogin),
               contentColor = Color.White
            ),
            shape = RoundedCornerShape(15.dp),
            content = {
               Text(
                  "Log in",
                  style = MaterialTheme.typography.h4.copy(
                     shadow = Shadow(
                        color = Color.Gray,
                        offset = Offset(x = 0f, y = 2f),
                        blurRadius = 0.2f,

                        )
                  ),
                  fontWeight = FontWeight.Black,
                  fontFamily = FontFamily.SansSerif,
                  fontSize = 20.sp
               )
            },

            )

         ClickableText(
            text = AnnotatedString("Don’t have an account? Sign Up"),
            onClick = {
                navController.navigate(Screens.Sign.route)
               //navController.navigate(Screens.Navigate.route)
            },
            modifier = Modifier
               .fillMaxHeight(200f)
               .padding(30.dp, 100.dp, 0.dp, 0.dp)
               .size(400.dp, 30.dp)
               .padding(8.dp)
            ,
            style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
         )
      }
   }
}


