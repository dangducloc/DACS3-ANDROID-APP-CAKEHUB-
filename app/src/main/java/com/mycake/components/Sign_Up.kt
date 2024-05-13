package com.mycake.components

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mycake.R
import com.mycake.components.include.row_b_f
import com.mycake.ui.theme.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.mycake.API_stuff.models.sign_up_req
import com.mycake.API_stuff.retrofit_and_more.API_Manager


@Preview
@Composable
fun Preview2(navController: NavHostController = rememberNavController()) {
    SignUp(navController)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController: NavHostController){
    val conTex = LocalContext.current
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var passwd by remember { mutableStateOf(TextFieldValue("")) }
    var passwd2 by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.landing_img),
                contentScale = ContentScale.FillBounds
            )
            .background(Black_filter)){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 70.dp, 20.dp, 0.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text="Hope you have",
                fontSize = 50.sp ,
                color = White,
                modifier = Modifier.padding(0.dp,20.dp,0.dp,0.dp)
            )
            Text(text="a good time",
                fontSize = 50.sp ,
                color = White,
                modifier = Modifier.padding(0.dp,0.dp,0.dp,20.dp)
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .padding(10.dp, 0.dp), color = White
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = username,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    username = it
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.AccountCircle,
                        contentDescription = "username")
                },
                colors = TextFieldDefaults.textFieldColors( containerColor = Almost_tranparent),
                textStyle = TextStyle(color = Black),
                label = { Text(text = "User name") },
                placeholder = { Text(text = "User_name") },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = email,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    email = it
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Mail,
                        contentDescription = "Email")
                },
                colors = TextFieldDefaults.textFieldColors( containerColor = Almost_tranparent),
                textStyle = TextStyle(color = Black),
                label = { Text(text = "Your Email") },
                placeholder = { Text(text = "Email") },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = passwd,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    passwd = it
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Edit,
                        contentDescription = "pass")
                },
                colors = TextFieldDefaults.textFieldColors( containerColor = Almost_tranparent),
                textStyle = TextStyle(color = Black),
                label = { Text(text = "Password") },
                placeholder = { Text(text = "Password") },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(icon, contentDescription = "Toggle password visibility")
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = passwd2,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    passwd2 = it
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Edit,
                        contentDescription = "pass2")
                },
                colors = TextFieldDefaults.textFieldColors( containerColor = Almost_tranparent),
                textStyle = TextStyle(color = Black),
                label = { Text(text = "Re-enter Password") },
                placeholder = { Text(text = "Re-enter Password") },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(icon, contentDescription = "Toggle password visibility")
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(2.dp)
                    .padding(20.dp, 0.dp), color = White
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                 signUp_handle(navController,conTex,username,email,passwd, passwd2)
                             },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Almost_tranparent,
                    contentColor = White)
            ) {
                Text(text = "Sign up",
                    textAlign=TextAlign.Center,
                    fontSize = 20.sp,
                    color = Black_filter,
                    modifier = Modifier)
            }
            Spacer(modifier = Modifier.height(70.dp))
            row_b_f(
                back = "Back",
                fun1 = { navController.popBackStack() },
                forward = "Login",
                fun2 = { navController.navigate("login")})
        }
    }
}


fun signUp_handle(navController: NavHostController,
                  conTex: Context,
                  username: TextFieldValue,
                  email: TextFieldValue,
                  passwd: TextFieldValue,
                  passwd1: TextFieldValue){
    val name: String = username.text // Accessing text property of TextFieldValue
    val pass: String = passwd.text // Accessing text property of TextFieldValue
    val email: String = email.text
    val pass1: String = passwd1.text
    if (name.isEmpty() || pass.isEmpty() || email.isEmpty() || pass1.isEmpty()) {
        Toast.makeText(conTex, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
    }else{
        val apiService = API_Manager()
        val input = sign_up_req(name,email,pass,pass1)
        Log.d("aaaa", input.toString())
        apiService.signup(input){
            Log.d("melpa", it.toString())
            if (it != null) {
                if (it.signup == true) {
                    Toast.makeText(conTex, "signing up as $name", Toast.LENGTH_SHORT).show()

                    navController.navigate("login")
                }else{
                    Toast.makeText(conTex, "fail", Toast.LENGTH_SHORT).show()

                }
            }else{
                Toast.makeText(conTex, "fail2", Toast.LENGTH_SHORT).show()

            }
        }
    }
//    return flag
}
