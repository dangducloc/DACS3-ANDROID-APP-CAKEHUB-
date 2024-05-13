package com.mycake.components

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.mycake.API_stuff.retrofit_and_more.API_Manager
import com.mycake.components.activies_Jump.dataShare
import com.mycake.components.include.BottomBar
import com.mycake.components.include.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Contract(navController: NavController,dataShare:dataShare){
    val apiService: API_Manager = remember { API_Manager() }
    val mUrl = apiService.host()+"contract"
    Scaffold(
        bottomBar = {  BottomBar(navController,dataShare) },
        topBar = { TopBar(navController)  },
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 60.dp)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item{Spacer(modifier = Modifier.height(99.dp))}
            item {
                WebContract(mUrl = mUrl)
            }
        }

    }

}
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebContract(mUrl:String){
    // Adding a WebView inside AndroidView
    // with layout as full screen
    AndroidView(factory = {
        WebView(it).apply {
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(mUrl)
        }
    }, update = {
        it.loadUrl(mUrl)
    })
}