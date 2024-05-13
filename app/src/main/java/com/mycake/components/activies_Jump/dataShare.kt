package com.mycake.components.activies_Jump

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.mycake.API_stuff.models.cake
import com.mycake.API_stuff.models.login_result


class dataShare:ViewModel() {
    var user by mutableStateOf<login_result?>(null)
        private set
    fun setuser(new_user:login_result){
        user = new_user
    }

    var type by mutableIntStateOf(0)
        private set

    fun settype(num:Int){
        type = num

    }

    var cake by mutableStateOf<cake?>(null)
        private set
    fun set_Cake (newcake:cake){
        cake = newcake
    }

}