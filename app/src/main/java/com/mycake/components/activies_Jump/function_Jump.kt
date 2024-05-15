package com.mycake.components.activies_Jump

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mycake.components.Cake_detail
import com.mycake.components.Carts_menu
import com.mycake.components.Contract
import com.mycake.components.Landing_page
import com.mycake.components.Login
import com.mycake.components.Main_menu
import com.mycake.components.Order
import com.mycake.components.Order_Detail
import com.mycake.components.SignUp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Main_Jump() {
    val navController = rememberNavController()
    val dataShare:dataShare = viewModel( )
    NavHost(navController, startDestination = "landing")
//    NavHost(navController, startDestination = "main_menu")
    {
        composable("landing") { Landing_page(navController = navController) }
        composable("login") { Login(navController = navController,dataShare = dataShare) }
        composable("signup") { SignUp(navController = navController)}
        composable("main_menu"){ Main_menu(navController = navController,dataShare = dataShare)}
        composable("carts_menu"){ Carts_menu(navController = navController,dataShare = dataShare)}
        composable("cake_detail"){ Cake_detail(navController = navController, dataShare = dataShare )}
        composable("contract"){ Contract(navController = navController, dataShare = dataShare)}
        composable("orders"){ Order(navController =navController, dataShare = dataShare)}
        composable("order_detail"){ Order_Detail(navController =navController, dataShare = dataShare)}

    }
}



