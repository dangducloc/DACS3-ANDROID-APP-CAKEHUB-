package com.mycake.components.include
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.mycake.API_stuff.models.type
import com.mycake.API_stuff.retrofit_and_more.API_Manager
import com.mycake.components.activies_Jump.dataShare
import com.mycake.ui.theme.*


@Preview
@Composable
fun Preview2() {

}

@Composable
fun BottomBar(navController: NavController, dataShare: dataShare){

    var showDialog by remember { mutableStateOf(false) }

    val apiService = remember { API_Manager() }
    var types by remember {
        mutableStateOf<ArrayList<type>?>(null)
    }

    LaunchedEffect(Unit) {
        apiService.gettypes { fetchedTypes ->
            types = fetchedTypes
        }
    }

    val selectedIndex = remember { mutableIntStateOf(0) }

    BottomNavigation(
        elevation = 10.dp,
        backgroundColor = Backgound,
        modifier = Modifier
            .clip(
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
        ) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.ShoppingCart,"")
        },
            unselectedContentColor = White,
            selectedContentColor = Black ,
            label = {Text(text = "Cart")} ,
            selected = (selectedIndex.intValue == 1),
            onClick = {
                selectedIndex.intValue = 1
                navController.navigate("carts_menu")
            })


        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Dashboard,"")
        },
            unselectedContentColor = White,
            selectedContentColor = Black ,
            label = { Text(text = "Menu") },
            selected = (selectedIndex.intValue == 0),
            onClick = {
                selectedIndex.intValue = 0
                showDialog = true
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.ContactEmergency,"")
        },
            unselectedContentColor = White,
            selectedContentColor = Black ,
            label = { Text(text = "Contact") },
            selected = (selectedIndex.intValue == 2),
            onClick = {
                selectedIndex.intValue = 2
                navController.navigate("contract")
            })
    }
    if (showDialog) {
        val icons = listOf(
            com.mycake.R.drawable._1,
            com.mycake.R.drawable._2,
            com.mycake.R.drawable._3,
            com.mycake.R.drawable._4,
            com.mycake.R.drawable._5,
        )
        Dialog(onDismissRequest = {showDialog = false}) {
            // Custom shape, background, and layout for the dialog
            Surface(
                shape = RoundedCornerShape(16.dp),
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Choose the type you want",

                    )
                    Spacer(modifier = Modifier.height(16.dp)) // Add spacing between title and buttons
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        onClick = {
                            dataShare.settype(num = 0 )
                            navController.navigate("main_menu")
                            showDialog = false
                        },

                        ) {
                        Text("All types")
                    }
                    types?.let { typeList ->
                        typeList.forEach { type ->
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                onClick = {
                                    val flag = type.id ?: 0 // Default value if id is null
                                    dataShare.settype(num = flag )
                                    navController.navigate("main_menu")
                                    showDialog = false
                                },

                            ) {
                                Row(modifier =Modifier.fillMaxWidth()){
                                    Image(painter = painterResource(id=icons[type.id?.minus(1)!!]), contentDescription = null )
                                    Text(type.Type ?: "Unknown",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center)
                                }
                            }
                        }
                    } ?: run {
                        Text(
                            text = "No types available",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController){
    CenterAlignedTopAppBar(
        modifier = Modifier.shadow(
            elevation = 6.dp,
            shape = RoundedCornerShape(
                bottomEnd = 16.dp,
                bottomStart = 16.dp
                ),
            ambientColor = White
        ),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Backgound,
            titleContentColor = primary,

        ),
        title = {
            Text(
                text="Cakehub",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 40.sp
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = White
                )
            }
        },
        actions = {
            IconButton(onClick = {
                navController.navigate("orders")
            }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = White
                )
            }

        },
    )
}