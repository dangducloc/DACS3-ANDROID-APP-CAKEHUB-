package com.mycake.components.include


import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.mycake.API_stuff.models.cart_item
import com.mycake.API_stuff.models.delete_CartItem_req
import com.mycake.API_stuff.models.update_cartItem_req
import com.mycake.API_stuff.retrofit_and_more.API_Manager
import com.mycake.ui.theme.*


@Preview
@Composable
fun Preview9(){
    val cart = cart_item(
        userid = 16,
        idCake = 7,
        Amount = 7,
        name = "KitKat Chocolate Truffle Cake",
        price = 32,
        src = "7.jpg"
        )
    val apiService: API_Manager = remember { API_Manager() }
    CartItem(iduser = 16,
        apiService=apiService,
        baseHost = "http://192.168.88.157:3000/",
        cart = cart,
        navController = rememberNavController()
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CartItem(iduser:Int,
             apiService: API_Manager,
             baseHost:String,
             cart:cart_item,
             navController:NavController){
    val config = LocalConfiguration.current
    var amount by remember { mutableStateOf(TextFieldValue(cart.Amount.toString())) }
    var onchange by remember { mutableIntStateOf(0) }
    val screenWidth = config.screenWidthDp
    val keyboardController = LocalSoftwareKeyboardController.current
    var ondelete by remember{ mutableIntStateOf(0) }
    val toDel = delete_CartItem_req(
        iduser = iduser,
        idcake = cart.idCake
    )


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
        verticalAlignment = Alignment.CenterVertically
    )
    {
        AsyncImage(
            model = baseHost+cart.src,
            contentDescription = "",
            modifier = Modifier
                .size((screenWidth / 4).dp)
                .clip(RoundedCornerShape(20.dp)),
            )
        Column(
            Modifier
                .width((screenWidth / 3).dp)
                .padding(5.dp, 10.dp, 0.dp, 0.dp)){
            cart.name?.let { Text(text = it, fontSize = 15.sp) }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .padding(10.dp, 0.dp),
                color = White
            )
            Text("Price: $ "+cart.price.toString(),
                modifier = Modifier
                    .padding(10.dp,0.dp,0.dp,10.dp),
                fontSize = 11.sp )
        }
        Column(
            Modifier
                .width((screenWidth / 7).dp)
                .padding(0.dp, 0.dp, 0.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
                Text("Amount",
                    modifier = Modifier
                        .padding(0.dp),
                    fontSize = 10.sp )


            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .padding(10.dp, 5.dp),
                color = White
            )
            OutlinedTextField(
                value = amount,
                singleLine = true,

                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                ),
                onValueChange = {
                    newValue -> amount = newValue

                } ,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onchange = 1
                        if(amount.text.toInt()<=0){
                            onchange = 2
                        }
                        keyboardController?.hide()
                    }
                )
            )

        }

        Button(onClick = {
            ondelete = 1
        },
            modifier = Modifier
                .padding(15.dp, 0.dp, 0.dp, 0.dp)
                .size((screenWidth / 6).dp)
        ) {
           Icon(imageVector = Icons.Default.RemoveShoppingCart,
               contentDescription = null,
               modifier = Modifier.fillMaxSize()
           )

        }


    }
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .width(5.dp)
            .padding(20.dp, 0.dp),
        color = White
    )
    Spacer(modifier = Modifier.height(20.dp))

    if(onchange == 1 ){
        val req = update_cartItem_req(
            iduser=iduser,
            idcake = cart.idCake,
            amount = amount.text.toInt()
        )
        apiService.upDate_cartItem(req){
            if(it!!.updated_itemCart == true){
                navController.navigate("carts_menu")
            }

        }
    }
    if(onchange == 2) {
        Toast.makeText(LocalContext.current,"fail to update your item",Toast.LENGTH_SHORT).show()
        navController.navigate("carts_menu")
    }

    if(ondelete == 1){
        apiService.delete_CartItem(req = toDel){
            if(it!!.delete_item == true){
                navController.navigate("carts_menu")
            }else{
                ondelete = 2
            }
        }
    }
    if(ondelete == 2){
        //Toast.makeText(LocalContext.current,"something is wrong",Toast.LENGTH_SHORT).show()
        navController.navigate("carts_menu")
    }


}