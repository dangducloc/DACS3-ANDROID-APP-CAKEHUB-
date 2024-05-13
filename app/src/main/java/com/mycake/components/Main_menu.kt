package com.mycake.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider

import androidx.compose.material3.Text
import com.mycake.ui.theme.*

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mycake.API_stuff.models.cake
import com.mycake.API_stuff.retrofit_and_more.API_Manager
import com.mycake.components.activies_Jump.dataShare
import com.mycake.components.include.BottomBar
import com.mycake.components.include.TopBar
import com.mycake.components.include.Menu_item
import com.mycake.R


@Preview
@Composable
fun Preview3(navController: NavHostController = rememberNavController()){
    Main_menu(navController, dataShare())
}



//@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Main_menu(navController: NavHostController,dataShare: dataShare) {
    val apiService:API_Manager = remember { API_Manager() }
    val baseHost:String = apiService.host()
    var cakes by remember {
        mutableStateOf<ArrayList<cake>?>(null)
    }
    val flag:Int = dataShare.type

    //Get cake list
    LaunchedEffect(Unit) {
        apiService.getcakes { fetchedCakes ->
            cakes = fetchedCakes
        }
    }

    Scaffold(
        bottomBar = { BottomBar(navController,dataShare) },
        topBar = { TopBar(navController) },
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 60.dp)
    ) {

        Column(modifier= Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.bg_main),
                contentScale = ContentScale.FillBounds,
//                colorFilter = ColorFilter.tint(Black_filter1)
            )
            .background(Black_filter2),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier.height(99.dp))
            if (cakes != null) {
                LazyColumn(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    item{
                        HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(5.dp)
                            .padding(40.dp, 5.dp),
                        color = White
                    )}
                    items(cakes!!.size) { index ->
                        if(flag == 0){
                            Menu_item(
                                host = baseHost,
                                cake = cakes!![index],
                                navController = navController,
                                dataShare = dataShare
                                )
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .width(5.dp)
                                    .padding(40.dp, 5.dp),
                                color = White
                            )
                        }
                        else if(flag == cakes!![index].Type){
                            Menu_item(
                                host = baseHost,
                                cake = cakes!![index],
                                navController = navController,
                                dataShare = dataShare
                                )
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .width(5.dp)
                                    .padding(40.dp, 5.dp),
                                color = White
                            )
                        }
                    }
                }
            } else {
                Text(text = "Loading cakes...")
            }
        }

    }



}