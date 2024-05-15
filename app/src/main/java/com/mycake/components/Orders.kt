package com.mycake.components

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mycake.API_stuff.models.getOrders
import com.mycake.API_stuff.models.getOrders_req
import com.mycake.API_stuff.models.login_result
import com.mycake.API_stuff.retrofit_and_more.API_Manager
import com.mycake.components.activies_Jump.dataShare
import com.mycake.components.include.BottomBar
import com.mycake.components.include.Order_Item
import com.mycake.components.include.TopBar
import com.mycake.ui.theme.White

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun Order(navController: NavController,dataShare: dataShare){
    val config = LocalConfiguration.current
    val user: login_result? = dataShare.user
    val apiService: API_Manager = remember { API_Manager() }
    var orders by remember { mutableStateOf<ArrayList<getOrders>?>(null) }

    LaunchedEffect(Unit){
        apiService.getOrders(getOrders_req(iduser = user?.id)){
            ordersFetched-> orders = ordersFetched
        }
    }
    Log.d("testing", orders.toString())
    Scaffold(
        bottomBar = {  BottomBar(navController,dataShare) },
        topBar = { TopBar(navController)},
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 60.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(125.dp))
            Text(
                text = "${user!!.name}'s Orders",
                fontSize = 32.sp,
                modifier = Modifier.padding(start = 15.dp)
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(5.dp)
                    .padding(0.dp, 10.dp),
                color = White
            )

            if(orders!=null && user != null){
                LazyColumn(
                    modifier = Modifier
                        .height((config.screenHeightDp * 0.8).dp)
                        .fillMaxWidth(),
                    content = {
                        items(orders!!.size){
                            index-> Order_Item(
                             navController = navController,
                             dataShare=dataShare,
                             index = index+1,
                             order = orders!![index])
                        }
                })
            }

        }
    }
}