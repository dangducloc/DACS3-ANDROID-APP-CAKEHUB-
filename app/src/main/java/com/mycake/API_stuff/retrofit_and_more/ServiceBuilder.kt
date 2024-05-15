package com.mycake.API_stuff.retrofit_and_more

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()
    const val BaseHost:String = "http://192.168.88.157:3000/"//tiết kiệm kinh phí
//    const val BaseHost:String = "http://192.168.1.206:3000/"//can tin ktx
//    const val BaseHost:String = "http://192.168.10.77:3000/"
//  const val BaseHost:String = "http://192.168.1.105:3000/"//EA3650
//  const val BaseHost:String = "http://10.50.126.4:3000/"//wifi giang vien
//  const val BaseHost:String = "http://192.168.1.163:3000/"//zone7
//  const val BaseHost:String = "http://192.168.147.79:3000/"//4g
//  const val BaseHost:String = "http://192.168.1.42:3000/"//nhà ăn trường
    private val retrofit = Retrofit.Builder()
        .baseUrl(BaseHost)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }

}
