package com.mycake.components.include

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.mycake.API_stuff.models.cake
import com.mycake.API_stuff.retrofit_and_more.API_Manager
import com.mycake.components.activies_Jump.dataShare
import com.mycake.ui.theme.*

@Preview
@Composable
fun Preview4(){
    val apiService = remember { API_Manager() }
    val baseHost:String = apiService.host()
    val cake = cake(1,
        "Original Black Forest Cake",
        25,
        50,
        "Original Black Forest Cake: A traditional German dessert made with layers of chocolate sponge cake, whipped cream, cherries, and chocolate shavings.",
        1,"1.jpg","Egg-less Cakes")

    Menu_item(
        host = baseHost,
        cake = cake,
        navController = rememberNavController(),
        dataShare =dataShare()
    )

}

@Composable
fun Menu_item(host:String,cake: cake,navController: NavController,dataShare: dataShare){
    val config = LocalConfiguration.current
    Box(modifier = Modifier
        .size((config.screenWidthDp - 20).dp)
        .clickable {
            dataShare.set_Cake(newcake = cake)
            navController.navigate("cake_detail")
        }
        .clip(RoundedCornerShape(25.dp))
    ){
        AsyncImage(
            model = host+cake.src,
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(25.dp)),
            colorFilter = ColorFilter.tint(Black_filter1, blendMode = BlendMode.Darken)
        )
        cake.name?.let {
            Text(text = it,
                fontSize = 60.sp,
                color = White,
                textAlign = TextAlign.Center,
                lineHeight = 60.sp,
                modifier = Modifier.align(Alignment.Center))
        }
    }
}