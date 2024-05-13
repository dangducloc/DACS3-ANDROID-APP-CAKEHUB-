package com.mycake.components

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.mycake.API_stuff.models.cart_item
import com.mycake.API_stuff.models.checkout_req
import com.mycake.API_stuff.models.get_cart_req
import com.mycake.API_stuff.models.login_result
import com.mycake.API_stuff.models.paymentMethod
import com.mycake.API_stuff.retrofit_and_more.API_Manager
import com.mycake.components.activies_Jump.dataShare
import com.mycake.components.include.*
import com.mycake.R.drawable
import com.mycake.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun Carts_menu(navController: NavController,dataShare: dataShare){
    val user: login_result? = dataShare.user
    val apiService: API_Manager = remember { API_Manager() }
    var carts by remember {
        mutableStateOf<ArrayList<cart_item>?>(null)
    }
    var paymentMethods by remember {
        mutableStateOf<ArrayList<paymentMethod>?>(null)
    }

    var emptyCart by remember { mutableStateOf(false) }

    var select by remember { mutableStateOf("") }

    var address by remember { mutableStateOf("") }

    var addressCheck by remember { mutableStateOf(false) }

    var checkedOut by remember { mutableIntStateOf(0) }

    var wrongIdCake by remember { mutableIntStateOf(0) }

    var idpayment = 0//remember { mutableIntStateOf(0) }

    var showCheckout by remember { mutableStateOf(false) }



    LaunchedEffect(Unit){
        apiService.getCart(get_cart_req(iduser = user?.id)){ fetchedCart->
            carts = fetchedCart
        }
        apiService.get_paymentMetods { 
            fetchedPaymentMethods-> paymentMethods = fetchedPaymentMethods
        }
    }
    val config = LocalConfiguration.current

    val screenHeight = config.screenHeightDp

    val baseHost:String = apiService.host()

    var totalCost = 0
    carts?.forEach{ item->
        totalCost += item.Amount!! *item.price!!
    }
    Scaffold(
        bottomBar = {  BottomBar(navController,dataShare) },
        topBar = { TopBar(navController)  },
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 60.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(100.dp))
            if(carts != null && user != null){
                LazyColumn(modifier = Modifier.height((screenHeight*0.7).dp)){
                    if (carts!!.size > 0){
                        items(carts!!.size){index->
                            user.id?.let { iduser ->
                                CartItem(baseHost = baseHost,
                                    cart = carts!![index],
                                    apiService = apiService,
                                    iduser = iduser,
                                    navController = navController)
                            }
                        }    
                    }else{
                       item{
                           Column(
                               modifier = Modifier
                                   .fillMaxSize(),
                               verticalArrangement = Arrangement.Center,
                               horizontalAlignment = Alignment.CenterHorizontally
                           ){
                               Spacer(modifier = Modifier.height(100.dp))
                               Image(
                                   painter = painterResource(id = drawable.empty_cart),
                                   contentDescription = null,
                                   colorFilter = ColorFilter.tint(White),
                                   modifier = Modifier
                                       .size((config.screenWidthDp*0.7).dp)
                                       .padding( start = 65.dp)
                               )
                               Text(
                                   text = "Your cart is empty",
                                   modifier = Modifier.fillMaxWidth(),
                                   textAlign = TextAlign.Center
                               )
                           }

                       }
                    }
                    
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(5.dp)
                    .padding(0.dp, 10.dp),
                color = White
            )
            Button(onClick = {
                if(carts!!.size == 0){
                    emptyCart = true
                }else{
                    showCheckout = true
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Checkout Here")
            }
        }

        if (showCheckout) {
            Dialog(
                onDismissRequest = {showCheckout = false},
                properties = DialogProperties(
                    usePlatformDefaultWidth = false
                )
            ) {
                // Custom shape, background, and layout for the dialog
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Summary",
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                        )
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .width(2.dp)
                                .padding(0.dp, 10.dp),
                            color = White
                        )
                        Text(
                            text = "We accept:",
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                            )
//                        Text(text = paymentMethods.toString())
                        Row (modifier =Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween){
                            paymentMethods!!.forEach {
                                    method->
                                Column(modifier = Modifier,
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ){
                                    RadioButton(
                                        selected = (select == method.method.toString()),
                                        onClick = {
                                            select = method.method.toString()
                                            if(select == method.method.toString()){
                                                idpayment = method.id!!
                                                Log.d("payment_picked", idpayment.toString())
                                            }
                                        }
                                    )
                                    method.method?.let { it1 -> Text(text = it1) }
                                }
                            }

                        }
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .width(1.dp)
                                .padding(0.dp, 10.dp),
                            color = White
                        )
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
//                                .padding(vertical = 10.dp)
                        ){
                            Text(text = "Total cost:", modifier = Modifier.width((config.screenWidthDp*0.3).dp))
                            Text(
                                text = "$ $totalCost",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.width((config.screenWidthDp*0.7).dp)
                            )
                        }
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
//                                .padding(vertical = 10.dp)
                        ){
                            Text(text = "Shipping:", modifier = Modifier.width((config.screenWidthDp*0.3).dp))
                            Text(
                                text = "Free",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.width((config.screenWidthDp*0.7).dp)
                            )
                        }
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .width(1.dp)
                                .padding(0.dp, 10.dp),
                            color = White
                        )

                        TextField(
                            value = address,
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = {
                                address = it
                            },
                            leadingIcon = {
                                Icon(imageVector = Icons.Default.PinDrop,
                                    contentDescription = null)
                            },
                            colors = TextFieldDefaults.textFieldColors( containerColor = Almost_tranparent),
                            label = { Text(text = "Address") },
                            placeholder = { Text(text = "Your address") },
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true,
                        )
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .width(1.dp)
                                .padding(0.dp, 10.dp),
                            color = White
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            modifier =Modifier.fillMaxWidth(),
                            onClick = {
                                if(address == "" || idpayment == 0){
                                    addressCheck = true
                                }else{
                                    val req = checkout_req(
                                        iduser = user!!.id,
                                        address = address,
                                        payment = idpayment
                                    )
                                    apiService.CheckOut(req = req){
                                        if (it!!.checkedout == true){
                                           checkedOut = 1
                                        }else{
                                            if(it.wrongCake == null) {
                                                checkedOut = 2
                                            }else{
                                                checkedOut = 3
                                                wrongIdCake = it.wrongCake
                                            }
                                        }
                                    }
                                }
                            }
                        ) {
                            Text(text = "Check Out")
                        }
                    }
                }
            }

        }
    }
    if(emptyCart){
        Toast.makeText(LocalContext.current,"Your cart is empty",Toast.LENGTH_SHORT).show()
        emptyCart = false
        navController.navigate("main_menu")
    }
    if(addressCheck){
        Toast.makeText(LocalContext.current,"Please filled all the fields",Toast.LENGTH_SHORT).show()
        addressCheck = false
    }
    when(checkedOut){
        1-> {
            Toast.makeText(LocalContext.current,"Your cart is checked out",Toast.LENGTH_SHORT).show()
            checkedOut = 0
            navController.navigate("main_menu")
        }
        2-> {
            Toast.makeText(LocalContext.current,"Something went wrong",Toast.LENGTH_SHORT).show()
            checkedOut = 0
        }
        3-> {
            var wrong = ""
            for(item in carts!!){
                if(item.idCake == wrongIdCake){
                    wrong = item.name.toString()
                    break
                }
            }
            Toast.makeText(LocalContext.current,"Reduce amount of $wrong",Toast.LENGTH_SHORT).show()
            checkedOut = 0
        }

    }
    
}