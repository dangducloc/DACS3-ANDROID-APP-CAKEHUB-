package com.mycake.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.mycake.API_stuff.models.add_CartItem_req
import com.mycake.API_stuff.models.cake
import com.mycake.API_stuff.models.login_result
import com.mycake.API_stuff.retrofit_and_more.API_Manager
import com.mycake.components.activies_Jump.dataShare
import com.mycake.components.include.BottomBar
import com.mycake.components.include.TopBar
import com.mycake.ui.theme.*


@Preview
@Composable
fun Prev10(){
    Cake_detail(navController = rememberNavController(), dataShare = dataShare())
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Cake_detail(navController: NavHostController, dataShare: dataShare){
    val cake:cake? = dataShare.cake
    val user:login_result? = dataShare.user
    val apiService: API_Manager = remember { API_Manager() }
    val host:String = apiService.host()
    var amount by remember {
        mutableIntStateOf(0)
    }
    var cond by remember {
        mutableStateOf(false)
    }
    var good_cond by remember {
        mutableStateOf(false)
    }
    val config = LocalConfiguration.current
    Scaffold(
        bottomBar = {  BottomBar(navController,dataShare) },
        topBar = { TopBar(navController)  },
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 60.dp)
    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(secondary)
            ,
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            LazyColumn(
                modifier = Modifier.height((config.screenHeightDp-138).dp),
//                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                item {
                    AsyncImage(
                        model = host+cake!!.src ,
                        contentDescription = null,
                        modifier = Modifier.clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(5.dp)
                            .padding(40.dp, 5.dp),
                        color = White
                    )
                    cake.name?.let {
                        name -> Text(
                            text = name,
                            fontSize = 25.sp,
                        style = TextStyle(
                            shadow = Shadow(
                                color = White,
                                offset = Offset(2.0f, 5.0f),
                                blurRadius = 2f
                            )
                        )
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        Text(
                            text = "Type: ${cake.typename}",
                            fontSize = 12.sp,
                            lineHeight = 15.sp,
                            modifier = Modifier
                                .padding(0.dp)
                                .width((config.screenWidthDp * 0.5).dp),
                            style = TextStyle.Default.copy(
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            text = "Price: $${cake.price}",
                            fontSize = 12.sp,
                            lineHeight = 15.sp,
                            modifier = Modifier
                                .padding(0.dp)
                                .width((config.screenWidthDp * 0.5).dp),
                            style = TextStyle.Default.copy(
                                textAlign = TextAlign.Center
                            ),

                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier
                            .width((config.screenWidthDp * 0.9).dp)
                            .padding(vertical = 10.dp)
                        , color = White)
                    cake.info?.let{

                        info->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            Text(
                                fontSize = 12.sp,
                                text = "Description: ",
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                   // .width((config.screenWidthDp*0.4-10).dp)
                            )
                            Text(
                                text = info,
                                fontSize = 12.sp,
                                lineHeight = 15.sp,
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 2.dp)
                                  /*  .width((config.screenWidthDp*0.6 - 5).dp)*/,
                                style = TextStyle.Default.copy()
                            )
                        }
                    }
                    HorizontalDivider(
                        modifier = Modifier
                            .width((config.screenWidthDp * 0.9).dp)
                            .padding(vertical = 10.dp)
                        , color = White)
                }
                item {

                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement =  Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(
                            colors = IconButtonColors(
                                containerColor = danger,
                                contentColor = White,
                                disabledContainerColor = tranparent,
                                disabledContentColor = tranparent
                            ),
                            onClick = {
                                amount -= 1
                                if(amount < 0){
                                    amount = 0
                                }
                            }) {
                            Icon(
                                imageVector = Icons.Filled.Remove,
                                contentDescription = null,
                                tint = White
                            )
                        }

                        Text(
                            textAlign = TextAlign.Center,
                            text = amount.toString(),
                            modifier = Modifier
                                .width((config.screenWidthDp * 0.3).dp)
                                .height((config.screenWidthDp * 0.12).dp)
                                .border(
                                    width = 2.dp,
                                    color = White,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )

                        IconButton(
                            colors = IconButtonColors(
                                containerColor = danger,
                                contentColor = White,
                                disabledContainerColor = tranparent,
                                disabledContentColor = tranparent
                            ),
                            onClick = { amount += 1}
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = null ,
                                tint = White
                            )
                        }
                    }
                }

                item {

                    Button(

                        onClick = {
                            if(amount > 0){
                                val add_req= add_CartItem_req(
                                    iduser = user!!.id,
                                    idcake = cake!!.id,
                                    amount = amount,
                                )
                                apiService.add_CartItem(add_req){
                                    if(it!!.addCarted ==true){
                                        good_cond = true
                                    }
                                }
                            }else{
                                cond = true
                            }
                                  },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        Text(text = "Add to your cart")
                    }
                }

            }
            if(cond){
                Toast.makeText(LocalContext.current,"Amount can not be 0 !",Toast.LENGTH_SHORT).show()
                cond = false
            }
            if(good_cond){
                Toast.makeText(LocalContext.current,"Added to your cart",Toast.LENGTH_SHORT).show()
                good_cond = false
            }
        }
    }

}