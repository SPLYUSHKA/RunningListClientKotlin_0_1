package com.example.runlistandroidclient.screens


import android.graphics.Bitmap
import android.graphics.drawable.shapes.Shape
import android.window.SplashScreen
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.runlistandroidclient.R
import com.example.runlistandroidclient.ViewModel.MainViewModel
import com.example.runlistandroidclient.navigation.Screens
import com.example.runlistandroidclient.ui.theme.RunListAndroidClientTheme
import com.example.runlistandroidclient.utils.Constans
import kotlinx.coroutines.delay
import kotlin.time.measureTimedValue

@Composable
fun SplashScreen(navController: NavController, viewModel: MainViewModel){
    var starAnimate by remember{
        mutableStateOf(false)
    }
    val alphaAnimation = animateFloatAsState(
        targetValue = if(starAnimate)1f else 0f,
        animationSpec = tween(durationMillis = 3000)
    )
    LaunchedEffect(key1 = true)
    {
        starAnimate = true
        delay(4000)
        navController.navigate(Screens.Login.route)
    }

    Splash(alpha = alphaAnimation.value)

}

@Composable
fun Splash(alpha: Float)
{
 Box(
     modifier = Modifier
         .fillMaxSize()
         .paint(
             painter = painterResource(R.drawable.splashback),
             contentScale = ContentScale.FillBounds
         )
      /*   .background(
             brush = Brush.linearGradient(
                 colorStops = arrayOf(
                     0.0f to colorResource(R.color.back_1_splash_screen),
                     0.3f to colorResource(R.color.back_2_splash_screen),
                     0.5f to colorResource(R.color.back_3_splash_screen),
                     0.8f to colorResource(R.color.back_4_splash_screen),
                     1f to colorResource(R.color.back_5_splash_screen)

                 )
             )
         )*/

      ,
     contentAlignment = Alignment.Center
 ){
     Image(
         modifier = Modifier
             .size(350.dp, 350.dp)
             .alpha(alpha = alpha)
             .shadow(1.dp, RoundedCornerShape(10.dp), clip = true )
         ,
         bitmap = ImageBitmap.imageResource(R.drawable.logo),
         contentDescription ="Логотип приложения" )


 }

}

