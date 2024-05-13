package com.mycake.API_stuff.retrofit_and_more


import com.mycake.API_stuff.models.*
import retrofit2.Call
import retrofit2.http.*

interface RestAPI {
    @POST("api/login")
    fun login(@Body reqData: login_req): Call<login_result>

    @POST("api/signup")
    fun signup(@Body reqData: sign_up_req): Call<signup_result>

    @GET("api/cakes")
    fun getcakes():Call<ArrayList<cake>>

    @GET("api/Types")
    fun gettypes():Call<ArrayList<type>>

    @POST("api/getCart")
    fun getCart(@Body getCartReq: get_cart_req):Call<ArrayList<cart_item>>

    @POST("api/updateCart")
    fun update_CartItem(@Body updatecartItem: update_cartItem_req):Call<updateCart_item>

    @POST("api/deleteCart")
    fun delete_CartItem(@Body req:delete_CartItem_req):Call<delete_CartItem>

    @POST("api/addCart")
    fun add_CartItem(@Body req:add_CartItem_req):Call<add_CartItem>

    @GET("api/paymentMethod")
    fun get_paymentMetods():Call<ArrayList<paymentMethod>>
    
    @POST("/api/checkOut")
    fun checkOut(@Body req:checkout_req):Call<checkOut>
}