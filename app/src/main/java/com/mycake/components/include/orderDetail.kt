package com.mycake.components.include

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mycake.API_stuff.models.cake
import com.mycake.API_stuff.models.orderDetail
import com.mycake.components.activies_Jump.dataShare
import com.mycake.ui.theme.White

@Composable
fun Order_detail_item(baseHost:String,details:orderDetail, cake: cake, dataShare: dataShare, navController: NavController){
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .width(5.dp)
            .padding(20.dp, 0.dp),
        color = White
    )

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp, 5.dp, 0.dp, 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        AsyncImage(
            model = baseHost+details.src,
            contentDescription = "",
            modifier = Modifier
                .size((screenWidth / 4).dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable {
                    dataShare.set_Cake(newcake = cake)
                    navController.navigate("cake_detail")
                },
        )
        Column(
            modifier = Modifier
                .width((screenWidth/2.5).dp)
                .padding(5.dp, 10.dp, 0.dp, 0.dp)){
            details.name?.let { Text(text = it, fontSize = 15.sp) }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .padding(10.dp, 0.dp),
                color = White
            )
            Text("Price: $ "+details.price.toString(),
                modifier = Modifier
                    .padding(10.dp,0.dp,0.dp,10.dp),
                fontSize = 11.sp )
        }
        Column(
            modifier = Modifier
                .width((screenWidth / 4).dp)
                .padding(0.dp, 0.dp, 0.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text("Amount: ${details.Amount}",
                modifier = Modifier
                    .padding(0.dp),
                fontSize = 12.sp
                )
        }

    }
}