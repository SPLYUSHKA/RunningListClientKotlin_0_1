package com.example.runlistandroidclient.screens

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.runlistandroidclient.R
import com.example.runlistandroidclient.model.systeminfo
import com.example.runlistandroidclient.navigation.Screens

@Composable
fun AboutSystem(navController: NavController){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.aboutrunningback),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Image(
            modifier = Modifier.fillMaxWidth().align(End).size(150.dp).padding(120.dp,0.dp,0.dp,0.dp),
            bitmap = ImageBitmap.imageResource(R.drawable.righttopdesing),
            contentDescription = "desing"

        )
        LazyColumn(modifier = Modifier
            .background(Color.Transparent)
            .padding(0.dp,0.dp, 0.dp, 0.dp)){
            itemsIndexed(
                listOf
                    (
                    systeminfo("Суть сисетмы", "Планирование задач в удобном формате - в виде недели"),
                    systeminfo("Функции системы", "Добавление\nУдаление\nИзменение и перенос уже существующих задач"),
                    systeminfo("Польза системы", "Ваши задачи всегда под рукой.\nС этим приложением вы станете более продуктивнее"),

                            )){
                _,item->
                itemColumAboutSystem(item = item)
            }
        }
        Image(
            modifier = Modifier.fillMaxWidth().align(Start).size(200.dp).padding(0.dp,10.dp,180.dp,0.dp),
            bitmap = ImageBitmap.imageResource(R.drawable.leftdesing),
            contentDescription = "desing"

        )
    }


}
@Composable
fun itemColumAboutSystem(item: systeminfo){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(40.dp,3.dp,20.dp,0.dp)
            .border(BorderStroke(5.dp, Color.White), shape = RoundedCornerShape(10.dp))
            .size(300.dp, 140.dp)
    )
    {
        Text(text = item.Title, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold , fontFamily = FontFamily.SansSerif)
        Text(modifier=Modifier.padding(10.dp,0.dp,10.dp,0.dp) , text = item.Text, color = Color.White, fontSize = 18.sp,fontFamily = FontFamily.Serif)
    }
}
