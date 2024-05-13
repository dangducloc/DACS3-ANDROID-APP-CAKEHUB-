package com.mycake.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import com.mycake.ui.theme.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mycake.R
import com.mycake.components.include.row_b_f

@Preview
@Composable
fun Preview(navController: NavHostController = rememberNavController()) {
    Landing_page(navController)
}
@Composable
fun  Landing_page(navController: NavHostController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.landing_img),
                contentScale = ContentScale.FillBounds
            )
            .background(Black_filter)
    ){
        Column(modifier = Modifier
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center

        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Text(text = "FoodHub",
                color = White,
                fontSize = 60.sp,
                modifier = Modifier
                    .padding(30.dp,70.dp,0.dp,0.dp)
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .padding(20.dp,0.dp), color = White
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = About,
                color = White,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(30.dp,0.dp,30.dp,20.dp)
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(3.dp)
                    .padding(40.dp,0.dp), color = White
            )
            Spacer(modifier = Modifier.height(40.dp))
           row_b_f(
               back = "Sign Up",
               fun1 = { navController.navigate("signup") },
               forward = "Login",
               fun2 = {navController.navigate("login")}
           )
        }
    }
}
