package com.mycake.components

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mycake.API_stuff.models.cake
import com.mycake.API_stuff.models.getOrders
import com.mycake.API_stuff.models.login_result
import com.mycake.API_stuff.models.orderDetail
import com.mycake.API_stuff.models.orderDetail_req
import com.mycake.API_stuff.retrofit_and_more.API_Manager
import com.mycake.components.activies_Jump.dataShare
import com.mycake.components.include.BottomBar
import com.mycake.components.include.Order_detail_item
import com.mycake.components.include.TopBar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun Order_Detail(navController: NavController,dataShare: dataShare){

    val apiService:API_Manager = remember { API_Manager() }
    val host: String = apiService.host()
    val user:login_result = dataShare.user!!

    val order:getOrders = dataShare.order!!
    val idorder: Int = dataShare.order!!.orderID!!

    val config = LocalConfiguration.current

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val dateTime = LocalDateTime.parse(order.order_date, formatter)
    val time = "${dateTime.dayOfMonth}/${dateTime.monthValue}/${dateTime.year}"
    var details by remember { mutableStateOf<ArrayList<orderDetail>?>(null) }
    LaunchedEffect(Unit){
        apiService.orderDetail(req =  orderDetail_req(orderID = idorder)){
            result-> details =result
        }
    }
    Log.d("testing1", details.toString())
    Scaffold(
        bottomBar = {  BottomBar(navController,dataShare) },
        topBar = { TopBar(navController) },
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 60.dp),
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
                ) {
                Spacer(modifier = Modifier.height(125.dp))
                Text(
                    text = "${user!!.name}'s Orders",
                    fontSize = 32.sp,
                    modifier = Modifier.padding(start = 15.dp)
                )
                Text(text = "Delivery to ${order.address}")
                Text(text = "Checked out at: $time ")
                if(user != null && details != null){
                    LazyColumn(
                        modifier = Modifier.height((config.screenHeightDp*0.8).dp),
                        content ={
                         items(details!!.size){
                             val item = details!![it]
                             val cake = cake(
                                 id = item.idcake,
                                 name = item.name,
                                 info = item.info,
                                 left_Amount = item.left_Amount,
                                 price = item.price,
                                 typename = item.typename,
                                 Type = item.Type,
                                 src = item.src
                             )
                             Order_detail_item(
                                 baseHost = host,
                                 details = item ,
                                 cake = cake,
                                 dataShare = dataShare,
                                 navController = navController
                             )
                         }
                        }
                    )
                }

            }
            Text(text = idorder.toString() )
        }
    ) 
    
}