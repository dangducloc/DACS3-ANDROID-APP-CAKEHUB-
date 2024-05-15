package com.mycake.components.include

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mycake.API_stuff.models.getOrders
import com.mycake.components.activies_Jump.dataShare
import com.mycake.ui.theme.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun Pre(){
    val a = getOrders(
        orderID = null,
        userID = 16,
        address = "Le Van Hien",
        name = "Linh",
        method = "paypal",
        order_date = "2024-03-09T23:08:17.000Z",
        payment = 1,
        status = 0
    )
    Order_Item(
        navController = rememberNavController(),
        dataShare = dataShare(),
        index = 1,
        order = a)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Order_Item(navController: NavController,dataShare: dataShare,index:Int,order:getOrders){

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val dateTime = LocalDateTime.parse(order.order_date, formatter)
    var a by remember {
        mutableStateOf(false)
    }
    val time = "${dateTime.dayOfMonth}/${dateTime.monthValue}/${dateTime.year}"

    Column {
        Row(modifier = Modifier.fillMaxWidth()){
            if(order.status == 0 ){
                Text(
                    text = "Processing",
                    color = White,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .padding(start = 10.dp),
                    textAlign = TextAlign.Start
                )
            }else{
                Text(
                    text = "Done",
                    color = White,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .padding(start = 10.dp),
                    textAlign = TextAlign.Start
                )
            }
            Text(
                text = time,
                color = White,
                fontSize = 10.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                textAlign = TextAlign.End
            )
        }
        Row (
            modifier = Modifier
                .clickable {
                    a = true
                    order.orderID?.let { dataShare.set_Order(neworder = order) }
                    navController.navigate("order_detail")
                }
                .padding(
                    top = 0.dp,
                    start = 20.dp,
                    end = 0.dp
                )
                .clip(shape = RoundedCornerShape(topStart = 40.dp, bottomStart = 10.dp))
                .background(secondary)
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = White,
                    shape = RoundedCornerShape(topStart = 40.dp, bottomStart = 10.dp)
                )
                .shadow(elevation = 1000.dp, shape = RoundedCornerShape(10.dp), clip = false)
            ,
            verticalAlignment = Alignment.CenterVertically
        ){

            Text(
                text = index.toString(),
                fontSize = 32.sp,
                color = White,
                modifier = Modifier
                    .padding(start = 15.dp),
            )
            Column{
                Text(
                    text = "Delivery to: ${order.address}",
                    color = White,
                    modifier = Modifier
                        .padding(start = 15.dp),
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(5.dp)
                        .padding(0.dp, 10.dp),
                    color = White
                )
                Text(
                    text = "Payment method: ${order.method}",
                    color = White,
                    modifier = Modifier
                        .padding(start = 15.dp),
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .width(5.dp)
                .padding(0.dp, 10.dp),
            )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .width(5.dp)
                .padding(horizontal = 10.dp),

            )
    }

    if(a){
//        Toast.makeText(LocalContext.current,order.orderID.toString(),Toast.LENGTH_SHORT).show()
        a= false
    }
}