package com.mycake.components.include

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mycake.ui.theme.White

@Composable
fun row_b_f(back:String, fun1:()->Unit , forward:String, fun2:()->Unit ){
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = fun1,
                modifier= Modifier.size(60.dp),  //avoid the oval shape
                shape = CircleShape,
                border= BorderStroke(2.dp, White),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(contentColor = White)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = back,
                    modifier = Modifier.size(45.dp)
                )
            }
            Text(text = back, color = White)
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = fun2,
                modifier= Modifier.size(60.dp),  //avoid the oval shape
                shape = CircleShape,
                border= BorderStroke(2.dp, White),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(contentColor = White)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = forward,
                    modifier = Modifier.size(45.dp)
                )
            }
            Text(text = forward, color = White)
        }
    }

}