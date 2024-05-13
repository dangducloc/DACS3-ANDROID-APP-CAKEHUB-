package com.mycake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mycake.components.Landing_page
import com.mycake.ui.theme.MyCakeTheme
import androidx.navigation.compose.rememberNavController
import com.mycake.components.activies_Jump.Main_Jump
import com.mycake.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCakeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Main_bg
                ) {
                   Main_Jump()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun default(){
    Landing_page(navController = rememberNavController())
}
}
